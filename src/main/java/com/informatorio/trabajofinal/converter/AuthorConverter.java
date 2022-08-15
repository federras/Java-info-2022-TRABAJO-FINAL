package com.informatorio.trabajofinal.converter;

import com.informatorio.trabajofinal.domain.Author;
import com.informatorio.trabajofinal.dto.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public AuthorDTO toDto(Author author) {
        return new AuthorDTO(author.getId(),
                author.getFirstname(),
                author.getLastname(),
                author.getFullname(),
                author.getCreatedAt());
    }

}
