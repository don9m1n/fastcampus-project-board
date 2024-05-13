package com.fastcampus.projectboard.domain.article.dto.request;

import com.fastcampus.projectboard.domain.article.dto.ArticleDto;
import com.fastcampus.projectboard.domain.article.model.Article;
import com.fastcampus.projectboard.domain.user.dto.UserAccountDto;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequest {

    private String title;
    private String content;
    private String hashtag;


    public static ArticleRequest of(String title, String content, String hashtag) {
        return new ArticleRequest(title, content, hashtag);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtag
        );
    }
}
