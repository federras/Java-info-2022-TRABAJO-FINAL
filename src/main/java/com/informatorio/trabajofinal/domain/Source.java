package com.informatorio.trabajofinal.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String code;
    private LocalDate createdAt;

    @ManyToMany(mappedBy = "sources", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    public Source(String name, LocalDate createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        setCode(name);
    }

    public Source() {
    }

    public Integer getId() {
        return id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
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

    public void setCode(String name) {
        name = name.toLowerCase();
        name = name.replace(" ", "-");
        this.code = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                ", articles=" + articles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) && Objects.equals(name, source.name) && Objects.equals(code, source.code) && Objects.equals(createdAt, source.createdAt) && Objects.equals(articles, source.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createdAt, articles);
    }
}
