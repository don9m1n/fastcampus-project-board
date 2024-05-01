package com.fastcampus.projectboard.domain.articlecomment;

import com.fastcampus.projectboard.domain.article.Article;

import java.time.LocalDateTime;

public class ArticleComment {

    private Long id;

    private Article article;

    private String content;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;
}
