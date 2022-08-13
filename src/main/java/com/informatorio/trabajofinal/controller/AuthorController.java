package com.informatorio.trabajofinal.controller;

import com.informatorio.trabajofinal.domain.Author;
import com.informatorio.trabajofinal.repository.AuthorRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/author")
    public Author createAuthor(@RequestBody Author author) {
        author = new Author(author.getFirstname(), author.getLastname(), author.getCreatedAt());
        return authorRepository.save(author);
    }

    @DeleteMapping("/author/{id}")
    public Optional<Author> deleteAuthor(@PathVariable Integer id) {
        Optional<Author> capture = authorRepository.findById(id);
        authorRepository.deleteById(id);
        return capture;
    }

    @PutMapping("/author/{id}")
    public Author updateAuthor(@PathVariable Integer id, @RequestBody Author newAuthor) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setFirstname(newAuthor.getFirstname());
            author.setLastname(newAuthor.getLastname());
            author.setFullname(newAuthor.getFirstname(), newAuthor.getLastname());
            author.setCreatedAt(newAuthor.getCreatedAt());
        }
        return authorRepository.save(author);
    }

    @GetMapping("/author/pageable")
    public List<Author> getAllAuthorsPageable(@RequestParam int page, @RequestParam int size) {
        return authorRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/author/date:{date}")
    public List<Author> getAllAuthorBeforeDate(@PathVariable String date) {
        LocalDate newDate = LocalDate.parse(date);;
        List<Author> authors = authorRepository.findAll().stream()
                .filter(author -> author.getCreatedAt().isAfter(newDate))
                .collect(Collectors.toList());
        return authors;
    }

    @GetMapping("/author/str:{string}")
    public List<Author> getAllAuthorWithStringInFullname(@PathVariable CharSequence string) {
        List<Author> authors = authorRepository.findAll().stream()
                .filter(author -> author.getFullname().contains(string))
                .collect(Collectors.toList());
        return authors;
    }


}
