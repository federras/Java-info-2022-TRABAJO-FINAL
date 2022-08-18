package com.informatorio.trabajofinal.controller;

import com.informatorio.trabajofinal.converter.ArticleConverter;
import com.informatorio.trabajofinal.converter.AuthorConverter;
import com.informatorio.trabajofinal.converter.SourceConverter;
import com.informatorio.trabajofinal.domain.Article;
import com.informatorio.trabajofinal.domain.Source;
import com.informatorio.trabajofinal.dto.ArticleDTO;
import com.informatorio.trabajofinal.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;
    private final AuthorConverter authorConverter;
    private final SourceConverter sourceConverter;

    @Autowired
    public ArticleController(ArticleRepository articleRepository,
                             ArticleConverter articleConverter,
                             AuthorConverter authorConverter,
                             SourceConverter sourceConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.authorConverter = authorConverter;
        this.sourceConverter = sourceConverter;
    }

    @PostMapping("/article")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody @Valid ArticleDTO articleDTO) {
        Article article = articleConverter.toEntity(articleDTO);
        articleRepository.save(article);
        return new ResponseEntity<>(articleConverter.toDto(article), HttpStatus.CREATED);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        Article capture =  articleRepository.findById(id).orElse(null);
        if (capture != null) {
            articleRepository.deleteById(id);
            return new ResponseEntity<>(articleConverter.toDto(capture), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Article inexistente", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        Article capture = articleRepository.findById(id).orElse(null);
        if (capture != null) {
            capture.setTitle(articleDTO.getTitle());
            capture.setDescription(articleDTO.getDescription());
            capture.setUrl(articleDTO.getUrl());
            capture.setUrlToImage(articleDTO.getUrlToImage());
            capture.modifyPublishedAt(articleDTO.getPublishedAt());
            capture.setContent(articleDTO.getContent());
            capture.setAuthor(authorConverter.toEntityExistent(articleDTO.getAuthor()));
            capture.setSources(sourceConverter.verifySourcesExist(articleDTO.getSources()));
            articleRepository.save(capture);
            return new ResponseEntity<>(articleConverter.toDto(capture), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Article inexistente", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/article/post/{id}")
    public ResponseEntity<?> postArticle(@PathVariable Long id) {
        Article capture = articleRepository.findById(id).orElse(null);
        if (capture != null) {
            if (capture.getPublishedAt() == null) {
                if (!capture.getSources().isEmpty()){
                    capture.setPublishedAt();
                    articleRepository.save(capture);
                    return new ResponseEntity<>(articleConverter.toDto(capture), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Le falta cargar el Source", HttpStatus.CONFLICT);
                }
            } else {
                return new ResponseEntity<>("Article Ya Publicado", HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>("Article inexistente", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/article", params = {"word"})
    public ResponseEntity<?> getAllArticlesContainsWord(@RequestParam String word) {
        Set<Article> capture = new HashSet<>();
        if (word.length() > 3) {
            String word2 = word.toLowerCase();
            Set<Article> capture1 = articleRepository.findAll()
                    .stream()
                    .filter(article -> article.getTitle().toLowerCase().contains(word2))
                    .collect(Collectors.toSet());
            Set<Article> capture2 = articleRepository.findAll()
                    .stream()
                    .filter(article -> article.getDescription().toLowerCase().contains(word2))
                    .collect(Collectors.toSet());
            Set<Article> capture3 = articleRepository.findAll()
                    .stream()
                    .filter(article -> article.getContent().toLowerCase().contains(word2))
                    .collect(Collectors.toSet());
            Set<Article> capture4 = articleRepository.findAll()
                    .stream()
                    .filter(article -> article.getAuthor().getFullname().toLowerCase().contains(word2))
                    .collect(Collectors.toSet());
            capture.addAll(capture1);
            capture.addAll(capture2);
            capture.addAll(capture3);
            capture.addAll(capture4);
            capture = capture.stream()
                    .filter(article -> Objects.nonNull(article.getPublishedAt()))
                    .collect(Collectors.toSet());
            return new ResponseEntity<>(articleConverter.toDto(capture), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La palabra debe tener mas de 3 letras", HttpStatus.CONFLICT);
        }
    }
}
