package com.informatorio.trabajofinal.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleDTO {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;
    private String urlToImage;
    private LocalDate publishedAt;
    @NotBlank
    private String content;
    @NotBlank
    private AuthorDTO author;
    private List<SourceDTO> sources = new ArrayList<>();

    public ArticleDTO(Long id,
                      String title,
                      String description,
                      String url,
                      String urlToImage,
                      LocalDate publishedAt,
                      String content,
                      AuthorDTO author,
                      List<SourceDTO> sources) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.author = author;
        this.sources = sources;
    }

    public ArticleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

   public List<SourceDTO> getSources() {
       return sources;
   }

   public void setSources(List<SourceDTO> sources) {
      this.sources = sources;
   }

    @Override
    public String toString() {
        return "ArticleDTO{" +
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
}
