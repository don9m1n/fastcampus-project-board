package com.fastcampus.projectboard.common.projection;

import com.fastcampus.projectboard.domain.articlecomment.model.ArticleComment;
import com.fastcampus.projectboard.domain.user.model.UserAccount;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "withUserAccount", types = UserAccount.class)
public interface UserAccountProjection {

    String getUserId();
    String getEmail();
    String getNickname();
    String getMemo();
    LocalDateTime getCreatedAt();
    String getCreatedBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();

}
