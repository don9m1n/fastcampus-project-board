package com.fastcampus.projectboard.domain.article.repository.querydsl;

import com.fastcampus.projectboard.domain.article.model.Article;
import com.fastcampus.projectboard.domain.article.model.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {

        QArticle article = QArticle.article;

        // null 이 아닌 해시태그만 조회
        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}
