package com.fastcampus.projectboard.domain.articlecomment.dto.request;

import com.fastcampus.projectboard.domain.articlecomment.dto.ArticleCommentDto;
import com.fastcampus.projectboard.domain.user.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleCommentRequest {

    private Long articleId;
    private Long parentCommentId;
    private String content;

    public static ArticleCommentRequest of(Long articleId, String content) {
        return ArticleCommentRequest.of(articleId, null, content);
    }

    public static ArticleCommentRequest of(Long articleId, Long parentCommentId, String content) {
        return new ArticleCommentRequest(articleId, parentCommentId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(articleId, userAccountDto, parentCommentId, content);
    }

}
