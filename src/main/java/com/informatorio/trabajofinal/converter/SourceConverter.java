package com.informatorio.trabajofinal.converter;

import com.informatorio.trabajofinal.domain.Source;
import com.informatorio.trabajofinal.dto.SourceDTO;
import com.informatorio.trabajofinal.repository.SourceRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SourceConverter {

    private final SourceRepository sourceRepository;

    public SourceConverter(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

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

    public Source toEntity(SourceDTO sourceDTO) {
        return new Source(sourceDTO.getName());
    }

    public Source verifySourceExist(SourceDTO sourceDTO) {
        return sourceRepository.findById(sourceDTO.getId()).orElse(null);
    }

    public List<Source> verifySourcesExist(List<SourceDTO> sourceDTOS) {
         List<Source> sources = sourceDTOS.stream()
                 .map(sourceDTO -> verifySourceExist(sourceDTO))
                 .collect(Collectors.toList());
         return sources;
    }
}
