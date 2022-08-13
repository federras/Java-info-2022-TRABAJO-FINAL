package com.informatorio.trabajofinal.repository;

import com.informatorio.trabajofinal.domain.Author;
import org.hibernate.boot.JaccPermissionDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, PagingAndSortingRepository<Author, Integer> {
}
