package io.petbook.pbboard.domain.board.article;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.format.DateTimeFormatter;

/**
 * [Kang] Article Reading Model
 */
public class ArticleInfo {
    @Getter
    @SuperBuilder
    public static class Main {
        // [Kang] React, Vue 등에서 여러 데이터들에 대해 렌더링할 때, Key 값을 설정하기 위한 값이다.
        private final String token;
        private final String title;
        private String context;

        // [Kang] TODO: UserToken 으로 사용자 이름, 별명 정보 불러와 주입 시키기
        private final String author;
    }

    @Getter
    @SuperBuilder
    public static class Brief extends Main {
        // [Kang] 리스트 등에서 불러오기 위한 단순 데이터
        private final String createdAt;

        private final Long viewCount; // [Kang] 아직 조회수 설계에 대해 고려를 안 했음.
        private final Long likeCount; // [Kang] 아직 좋아요 설계에 대해 고려를 안 했음.

        public static Brief toInfo(Article article) {
            return Brief.builder()
                    .token(article.getToken())
                    .title(article.isVisible() ? article.getTitle() : "[[제목 비공개]]")
                    .context(article.isVisible() ? article.contextBriefing() : "[[내용 비공개]]")
                    .author(article.getUserToken())
                    .createdAt(article.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .viewCount(0L)
                    .likeCount(0L)
                    .build();
        }
    }

    @Getter
    @SuperBuilder
    public static class Detail extends Main {
        // [Kang] 포스트 조회 등에서 불러오기 위한 복합 데이터
        private final String categoryTitle;
        private final String createdAt;
        private final String updatedAt;

        private final Long viewCount; // [Kang] 아직 조회수 설계에 대해 고려를 안 했음.
        private final Long likeCount; // [Kang] 아직 좋아요 설계에 대해 고려를 안 했음.

        // [Kang] TODO: 댓글 수, 댓글 목록 등등 반영 필요

        public static Detail toInfo(Article article) {
            return Detail.builder()
                    .token(article.getToken())
                    .title(article.isVisible() ? article.getTitle() : "[[제목 비공개]]")
                    .context(article.isVisible() ? article.getContext() : "[[내용 비공개]]")
                    .author(article.getUserToken())
                    .categoryTitle(article.getCategory().getTitle())
                    .createdAt(article.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .updatedAt(article.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .viewCount(0L)
                    .likeCount(0L)
                    .build();
        }
    }
}
