package com.informatorio.trabajofinal.controller;

import com.informatorio.trabajofinal.converter.ArticleConverter;
import com.informatorio.trabajofinal.domain.Article;
import com.informatorio.trabajofinal.dto.ArticleDTO;
import com.informatorio.trabajofinal.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    @PostMapping("/article")
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @DeleteMapping("/article/{id}")
    public ArticleDTO deleteArticle(@PathVariable Long id) {
        Article capture =  articleRepository.findById(id).orElse(null);
        if (capture != null) {
            articleRepository.deleteById(id);
        }
        return articleConverter.toDto(capture);
    }
}
