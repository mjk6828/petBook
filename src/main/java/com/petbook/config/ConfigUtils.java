package com.petbook.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
public class ConfigUtils {

    @Value("${google.auth.url}")
    private String googleAuthUrl;

    @Value("${google.login.url}")
    private String googleLoginUrl;

    @Value("${google.redirect.uri}")
    private String googleRedirectUrl;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.secret}")
    private String googleSecret;

    @Value("${google.auth.scope}")
    private String scopes;

    // GOogle 로그인 URL 생성 로직
    public String googleInitUrl() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", getGoogleClientId());
        params.put("redirect_uri", getGoogleRedirectUrl());
        params.put("response_type", "code");
        params.put("scope", getScopeUrl());

        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));

        return getGoogleLoginUrl()
                + "/o/oauth2/v2/auth"
                + "?"
                + paramStr;
    }

    // scope의 값을 보내기 위해 띄어쓰기 값을 UTF-8로 변환하는 로직 포함
    public String getScopeUrl() {

        return scopes.replaceAll(",", "%20");
    }


    @Value("${kakao.redirect.url}")
    private String kakaoRedirectUrl;

    @Value("${kakao.restapi.key}")
    private String kakaoRestKey;

    @Value("${kakao.login.url}")
    private String kakaoLoginUrl;

    public String kakaoInitUrl() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", kakaoRestKey);
        params.put("redirect_uri", kakaoRedirectUrl);
        params.put("response_type", "code");
        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));

        return kakaoLoginUrl
                + "?"
                + paramStr;
    }

    @Value("${naver.login.url}")
    private String naverLoginUrl;

    @Value("${naver.redirect.url}")
    private String naverRedirectUrl;

    @Value("${naver.client.id}")
    private String naverClientId;

    public String naverInitUrl() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", naverClientId);
        params.put("redirect_uri", naverRedirectUrl);
        params.put("response_type", "code");
        params.put("state", "state");
        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));
        return naverLoginUrl
                + "?"
                + paramStr;
    }
}