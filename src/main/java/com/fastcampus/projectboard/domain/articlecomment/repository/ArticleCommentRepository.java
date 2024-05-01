package com.fastcampus.projectboard.domain.articlecomment.repository;

import com.fastcampus.projectboard.domain.articlecomment.model.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
