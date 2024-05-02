package com.fastcampus.projectboard.domain.articlecomment.repository;

import com.fastcampus.projectboard.domain.articlecomment.model.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
