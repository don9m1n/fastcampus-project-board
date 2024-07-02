package com.fastcampus.projectboard.common.config;

import com.fastcampus.projectboard.domain.article.model.Article;
import com.fastcampus.projectboard.domain.articlecomment.model.ArticleComment;
import com.fastcampus.projectboard.domain.hashtag.model.Hashtag;
import com.fastcampus.projectboard.domain.user.model.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig((config, cors) -> config
                .exposeIdsFor(UserAccount.class)
                .exposeIdsFor(Article.class)
                .exposeIdsFor(ArticleComment.class)
                .exposeIdsFor(Hashtag.class)
        );
    }

}
