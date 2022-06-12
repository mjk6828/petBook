package io.petbook.pbboard.application;

import io.petbook.pbboard.domain.board.article.ArticleService;
import io.petbook.pbboard.domain.board.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleFacade {
    private final ArticleService articleService;
    private final CategoryService categoryService;

    public List<Object> loadArticleList() {
        // [Kang] 메소드는 가명.
        /**
         * 여기에는 각종 서비스들이 모여서, 각각 필요한 데이터들에 대해 처리 및 주입하는 과정을 취합한다.
         * 예를 들어 게시글 목록을 불러온다고 치자.
         * -> A 데이터베이스에서 게시글 목록 조회
         * -> B 데이터베이스에서 게시글 별 회원 정보 조회
         * -> A 데이터베이스와 B 데이터베이스 내용을 주입 (SeriesFactory 구성 필요)
         *
         * 참고로 백엔드 로직은 내구성을 높이지 말고, 외부에서도 예외를 던져서 ControllerAdvice 같은 곳에서 처리하는 게 더 낫다.
         * 웬만하면 try catch 문은 실무에서 사용하는 것을 요즘은 꺼려하는 편이다. (아무래도 MSA 트랜잭션으로 인한 데이터 정합성을 보장하기 위함이라 할까?)
         *
         * 여기에는 로직을 흐르게 하더라도, 일부분 오류가 발생 하더라도 큰 상관이 없는 로직 (쏟은 물을 주어 담을 수 없듯이) 을 작성하는게 좋다.
         * 예를 들어 사용자 가입이 완료되고, 사용자 수신 일부 정보 (이메일 같은 거) 를 활용한 API 연동을 할 때 이를 실행할 때 오류가 발생 하더라도, 사용자 가입은 이미 되어 있듯이.
         */
        return Collections.emptyList();
    }
}