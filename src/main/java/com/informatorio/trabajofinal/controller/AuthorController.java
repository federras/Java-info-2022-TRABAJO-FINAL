package com.informatorio.trabajofinal.controller;

import com.informatorio.trabajofinal.converter.AuthorConverter;
import com.informatorio.trabajofinal.domain.Author;
import com.informatorio.trabajofinal.dto.AuthorDTO;
import com.informatorio.trabajofinal.pageable.AuthorPage;
import com.informatorio.trabajofinal.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
    }

    @PostMapping("/author")
    public ResponseEntity<?> createAuthor(@RequestBody @Valid AuthorDTO authorDTO) {
        Author author = authorConverter.toEntity(authorDTO);
        authorRepository.save(author);
        return new ResponseEntity<>(authorConverter.toDto(author), HttpStatus.CREATED);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id) {
        Optional<Author> capture = authorRepository.findById(id);
        if (capture.isPresent()) {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(authorConverter.toDto(capture.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Author no existente", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id, @RequestBody @Valid AuthorDTO newAuthorDTO) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setFirstname(newAuthorDTO.getFirstname());
            author.setLastname(newAuthorDTO.getLastname());
            author.setFullname(newAuthorDTO.getFirstname(), newAuthorDTO.getLastname());
            author.setCreatedAt(newAuthorDTO.getCreatedAt());
            authorRepository.save(author);
            return new ResponseEntity<>(authorConverter.toDto(author), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Author inexistente", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/author")
    public ResponseEntity<?> getAllAuthorsPageable(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page pageResult = authorRepository.findAll(pageable);
        AuthorPage authorPage = new AuthorPage(pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                authorConverter.toDtoList(pageResult.getContent()));
        return new ResponseEntity<>(authorPage, HttpStatus.OK);
    }

    @GetMapping(value = "/author", params = {"date"})
    public ResponseEntity<List<AuthorDTO>> getAllAuthorBeforeDate(@RequestParam String date) {
        LocalDate newDate = LocalDate.parse(date);
        List<Author> authors = authorRepository.findAll().stream()
                .filter(author -> author.getCreatedAt().isAfter(newDate))
                .collect(Collectors.toList());
        return new ResponseEntity<>(authorConverter.toDtoList(authors), HttpStatus.OK);
    }

    @GetMapping(value = "/author", params = {"string"})
    public ResponseEntity<List<AuthorDTO>> getAllAuthorWithStringInFullname(@RequestParam String string) {
       List<Author> authors = authorRepository.findAll().stream()
                .filter(author -> author.getFullname().toLowerCase().contains(string.toLowerCase()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(authorConverter.toDtoList(authors), HttpStatus.OK);
    }
}
