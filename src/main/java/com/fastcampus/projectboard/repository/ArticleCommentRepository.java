package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment articleComment) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(articleComment.content, articleComment.createdAt, articleComment.createdBy);
        bindings.bind(articleComment.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(articleComment.createdAt).first(DateTimeExpression::eq);
        bindings.bind(articleComment.createdBy).first(StringExpression::containsIgnoreCase);
    }

}
