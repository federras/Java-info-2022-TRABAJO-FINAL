package com.informatorio.trabajofinal.pageable;

import com.informatorio.trabajofinal.dto.ArticleDTO;

import java.util.List;


public class AuthorPage {

    private int page;
    private int size;
    private Long totalElements;
    private int totalPage;

    private List<ArticleDTO> content;

    public AuthorPage(int page, int size, Long totalElements, int totalPage, List<ArticleDTO> content) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
        this.content = content;
    }

    public AuthorPage() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ArticleDTO> getContent() {
        return content;
    }

    public void setContent(List<ArticleDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPage=" + totalPage +
                ", content=" + content +
                '}';
    }
}
