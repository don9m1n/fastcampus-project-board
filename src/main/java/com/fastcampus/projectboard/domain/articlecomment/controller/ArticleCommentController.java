package com.fastcampus.projectboard.domain.articlecomment.controller;

import com.fastcampus.projectboard.common.dto.BoardPrincipal;
import com.fastcampus.projectboard.domain.articlecomment.dto.ArticleCommentDto;
import com.fastcampus.projectboard.domain.articlecomment.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.domain.articlecomment.service.ArticleCommentService;
import com.fastcampus.projectboard.domain.user.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(@AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                        ArticleCommentRequest articleCommentRequest) {

        ArticleCommentDto dto = articleCommentRequest.toDto(boardPrincipal.toDto());

        articleCommentService.saveArticleComment(dto);
        return "redirect:/articles/" + articleCommentRequest.getArticleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            @RequestParam Long articleId) {

        articleCommentService.deleteArticleComment(commentId, boardPrincipal.getUsername());

        return "redirect:/articles/" + articleId;
    }
}
