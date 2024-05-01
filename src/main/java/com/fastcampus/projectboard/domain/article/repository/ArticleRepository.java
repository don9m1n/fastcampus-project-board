package com.fastcampus.projectboard.domain.article.repository;

import com.fastcampus.projectboard.domain.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
