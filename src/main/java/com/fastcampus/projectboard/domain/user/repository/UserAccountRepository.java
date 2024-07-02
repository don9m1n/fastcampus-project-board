package com.fastcampus.projectboard.domain.user.repository;

import com.fastcampus.projectboard.common.projection.ArticleCommentProjection;
import com.fastcampus.projectboard.common.projection.UserAccountProjection;
import com.fastcampus.projectboard.domain.user.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {


}
