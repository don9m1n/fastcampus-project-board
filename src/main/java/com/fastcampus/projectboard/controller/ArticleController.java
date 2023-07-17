package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(Model model) {
        model.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{id}")
    public String article(@PathVariable Long id, Model model) {
        model.addAttribute("article", "article"); // todo: null을 넣으면 테스트시 오류 발생..
        model.addAttribute("articleComments", List.of());
        return "articles/detail";
    }

}
