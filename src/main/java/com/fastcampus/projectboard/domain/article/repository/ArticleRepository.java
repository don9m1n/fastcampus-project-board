package com.fastcampus.projectboard.domain.article.repository;

import com.fastcampus.projectboard.domain.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
