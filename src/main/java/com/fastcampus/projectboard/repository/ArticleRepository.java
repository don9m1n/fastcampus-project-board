package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.fastcampus.projectboard.repository.querydsl.ArticleRepositoryCustom;
import com.fastcampus.projectboard.repository.querydsl.ArticleRepositoryCustomImpl;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>,// 엔티티의 필드에 대한 모든 검색 기능 구현해줌
        QuerydslBinderCustomizer<QArticle> { // 추가적인 검색 기능을 위한 인터페이스

    // exact match 를 피하기 위해 like 쿼리를 사용하도록 구현
    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
    Page<Article> findByHashtag(String hashtag, Pageable pageable);

    void deleteArticleByIdAndUserAccount_UserId(Long articleId, String userId);

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
