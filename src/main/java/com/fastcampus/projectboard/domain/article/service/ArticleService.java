package com.fastcampus.projectboard.domain.article.service;

import com.fastcampus.projectboard.domain.article.dto.ArticleDto;
import com.fastcampus.projectboard.domain.article.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.domain.article.dto.ArticleWithCommentsDto;
import com.fastcampus.projectboard.domain.article.model.SearchType;
import com.fastcampus.projectboard.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long articleId) {
        return null;
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {

    }

    public void updateArticle(ArticleDto dto) {

    }

    public void deleteArticle(long articleId) {
    }
}
