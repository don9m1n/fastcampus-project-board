package com.fastcampus.projectboard.domain.articlecomment.controller;

import com.fastcampus.projectboard.domain.articlecomment.dto.ArticleCommentDto;
import com.fastcampus.projectboard.domain.articlecomment.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.domain.articlecomment.service.ArticleCommentService;
import com.fastcampus.projectboard.domain.user.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
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
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {

        // TODO: 인증 정보를 넣어줘야 한다.
        ArticleCommentDto dto = articleCommentRequest.toDto(UserAccountDto.of(
                "uno", "asdf1234", "uno@mail.com", "Uno", "memo", null, null, null, null
        ));

        articleCommentService.saveArticleComment(dto);
        return "redirect:/articles/" + articleCommentRequest.getArticleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, @RequestParam Long articleId) {

        // TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }
}
