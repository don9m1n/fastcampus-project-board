package com.fastcampus.projectboard.domain.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    @GetMapping
    public String articles(Model model) {
        model.addAttribute("articles", List.of());
        return "articles/index";
    }

}
