package com.informatorio.trabajofinal.converter;

import com.informatorio.trabajofinal.domain.Article;
import com.informatorio.trabajofinal.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {

    private final AuthorConverter authorConverter;
    private final SourceConverter sourceConverter;

    @Autowired
    public ArticleConverter(AuthorConverter authorConverter, SourceConverter sourceConverter) {
        this.authorConverter = authorConverter;
        this.sourceConverter = sourceConverter;
    }

    public ArticleDTO toDto(Article article) {
        return new ArticleDTO(article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublishedAt(),
                article.getContent(),
                authorConverter.toDto(article.getAuthor()),
                sourceConverter.listSourcesToDto(article.getSources()));
    }

    public Set<ArticleDTO> toDto(Set<Article> articles) {
        return articles.stream()
                .map(article -> toDto(article))
                .collect(Collectors.toSet());
    }

    public Article toEntity(ArticleDTO articleDTO) {
        return new Article(articleDTO.getTitle(),
                articleDTO.getDescription(),
                articleDTO.getUrl(),
                articleDTO.getUrlToImage(),
                articleDTO.getContent(),
                authorConverter.toEntityExistent(articleDTO.getAuthor()));
    }
}
