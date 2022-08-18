package com.informatorio.trabajofinal.dto;

import com.informatorio.trabajofinal.domain.Article;
import com.informatorio.trabajofinal.domain.Source;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SourceDTO {
    private Integer id;
    @NotBlank
    private String name;
    private String code;
    private LocalDate createdAt;

    private List<Article> articles = new ArrayList<>();


    public SourceDTO(Integer id, String name, String code, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createdAt = createdAt;
    }

    public SourceDTO() {
    }

  /*  public void addArticles(Article article){
        sources.stream()
                .forEach(source -> source.getArticles().add(article));
    };*/

    public void addArticle(Article article){
        this.articles.add(article);
    };

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SourceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceDTO sourceDTO = (SourceDTO) o;
        return Objects.equals(id, sourceDTO.id) && Objects.equals(name, sourceDTO.name) && Objects.equals(code, sourceDTO.code) && Objects.equals(createdAt, sourceDTO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createdAt);
    }
}
