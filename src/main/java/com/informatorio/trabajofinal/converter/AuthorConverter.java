package com.informatorio.trabajofinal.converter;

import com.informatorio.trabajofinal.domain.Author;
import com.informatorio.trabajofinal.dto.AuthorDTO;
import com.informatorio.trabajofinal.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthorConverter {

    private final AuthorRepository authorRepository;

    public AuthorConverter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO toDto(Author author) {
        return new AuthorDTO(author.getId(),
                author.getFirstname(),
                author.getLastname(),
                author.getCreatedAt());
    }

    public List<AuthorDTO> toDtoList(List<Author> authors) {
        return authors.stream()
                .map(author -> toDto(author))
                .collect(Collectors.toList());
    }

    public Author toEntity(AuthorDTO authorDTO) {
        Author author = new Author(authorDTO.getFirstname(),
                authorDTO.getLastname());
        return author;
    }

    public Author toEntityExistent(AuthorDTO authorDTO) {
        return authorRepository.findById(authorDTO.getId()).orElse(null);
    }
}
