package com.informatorio.trabajofinal.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String fullname;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Article> articles = new ArrayList<>();

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.createdAt = LocalDate.now();
        setFullname(firstname, lastname);
    }

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFullname(String firstname, String lastname) {
        this.fullname = firstname + " " + lastname;;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(firstname, author.firstname) && Objects.equals(lastname, author.lastname) && Objects.equals(fullname, author.fullname) && Objects.equals(createdAt, author.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, fullname, createdAt);
    }
}
