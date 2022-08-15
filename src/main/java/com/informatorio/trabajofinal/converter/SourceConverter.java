package com.informatorio.trabajofinal.converter;

import com.informatorio.trabajofinal.domain.Source;
import com.informatorio.trabajofinal.dto.SourceDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SourceConverter {

    public SourceDTO toDto(Source source) {
        return new SourceDTO(source.getId(),
                source.getName(),
                source.getCode(),
                source.getCreatedAt());
    }

    public List<SourceDTO> listSourcesToDto(List<Source> sources) {
        return sources.stream()
                .map(source -> toDto(source))
                .collect(Collectors.toList());
    }
}
