package com.informatorio.trabajofinal.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AuthorDTO {
    private Integer id;
    @NotEmpty
    @NotNull
    private String firstname;
    @NotEmpty
    @NotNull
    private String lastname;
    private String fullname;
    private LocalDate createdAt;

    public AuthorDTO(Integer id,
                     String firstname,
                     String lastname,
                     LocalDate createdAt){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        setFullname(firstname, lastname);
        this.createdAt = createdAt;
    }

    public AuthorDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String firstname, String lastname) {
        this.fullname = firstname + " " + lastname;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
