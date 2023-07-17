package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,// 엔티티의 필드에 대한 모든 검색 기능 구현해줌
        QuerydslBinderCustomizer<QArticle> { // 추가적인 검색 기능을 위한 인터페이스

    @Override
    default void customize(QuerydslBindings bindings, QArticle article) {
        // including 에 포함되지 않은 필드들의 검색 기능은 off
        bindings.excludeUnlistedProperties(true);
        // 검색 기능은 아래 5개의 필드에만 적용
        bindings.including(article.title, article.content, article.hashtag, article.createdAt, article.createdBy);
        bindings.bind(article.title).first((path, value) -> path.containsIgnoreCase(value));
        bindings.bind(article.content).first(StringExpression::containsIgnoreCase); // like '%{value}%'
        bindings.bind(article.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(article.createdAt).first(DateTimeExpression::eq);
        bindings.bind(article.createdBy).first(StringExpression::containsIgnoreCase);
    }

}
