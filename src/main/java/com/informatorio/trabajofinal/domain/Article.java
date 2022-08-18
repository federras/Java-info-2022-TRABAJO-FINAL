package com.informatorio.trabajofinal.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private LocalDate publishedAt;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "article_source",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id")
            )
    private List<Source> sources = new ArrayList<>();

    public Article(String title,
                   String description,
                   String url,
                   String urlToImage,
                   String content,
                   Author author,
                   List<Source> sources){
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = null;
        this.content = content;
        this.author = author;
        this.sources = sources;
        }

    public Article() {
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
        sources.stream()
                .forEach(source -> source.getArticles().add(this));
    }

   /*public void addSources(List<Source> sources) {
        this.sources = sources;
        sources.stream()
                .forEach(source -> source.getArticles().add(this));


        List<Source> sources = sourcesIds.stream()
                .map(id -> sourceRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        this.sources = sources;

        sources.stream()
                .forEach(source -> source.getArticles().add(this));
    }*/

    public Author getAuthor() {
        return author;
   }

   public void setAuthor(Author author) {
        this.author = author;
   }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt() {
        this.publishedAt = LocalDate.now();
    }

    public void modifyPublishedAt(LocalDate date) {
        if (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())) {
            this.publishedAt = date;
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(description, article.description) && Objects.equals(url, article.url) && Objects.equals(urlToImage, article.urlToImage) && Objects.equals(publishedAt, article.publishedAt) && Objects.equals(content, article.content);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", sources=" + sources +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, url, urlToImage, publishedAt, content, author, sources);
    }
}
