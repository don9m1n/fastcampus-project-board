package com.fastcampus.projectboard.common.projection;

import com.fastcampus.projectboard.domain.article.model.Article;
import com.fastcampus.projectboard.domain.user.model.UserAccount;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "withUserAccount", types = Article.class)
public interface ArticleProjection {

    Long getId();
    UserAccount getUserAccount();
    String getTitle();
    String getContent();
    LocalDateTime getCreatedAt();
    String getCreatedBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();

}
