package com.informatorio.trabajofinal.controller;

import com.informatorio.trabajofinal.converter.SourceConverter;
import com.informatorio.trabajofinal.domain.Source;
import com.informatorio.trabajofinal.dto.SourceDTO;
import com.informatorio.trabajofinal.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SourceController {

    private final SourceRepository sourceRepository;
    private final SourceConverter sourceConverter;

    @Autowired
    public SourceController(SourceRepository sourceRepository, SourceConverter sourceConverter) {
        this.sourceRepository = sourceRepository;
        this.sourceConverter = sourceConverter;
    }

    @PostMapping("/source")
    public SourceDTO createSource(@RequestBody Source source) {
        source = new Source(source.getName(), source.getCreatedAt());
        sourceRepository.save(source);
        return sourceConverter.toDto(source);
    }

    @DeleteMapping("/source/{id}")
    public SourceDTO deleteSource(@PathVariable Integer id) {
        Source capture = sourceRepository.findById(id).orElse(null);
        sourceRepository.deleteById(id);
        return sourceConverter.toDto(capture);
    }

    @PutMapping("/source/{id}")
    public SourceDTO updateSource(@PathVariable Integer id, @RequestBody Source newSource) {
        Source source = sourceRepository.findById(id).orElse(null);
        if (source != null) {
            source.setName(newSource.getName());
            source.setCode(newSource.getName());
            source.setCreatedAt(newSource.getCreatedAt());
        }
        sourceRepository.save(source);
        return sourceConverter.toDto(source);
    }

    @GetMapping("/source")
    public List<SourceDTO> getAllSources() {
        return sourceConverter.listSourcesToDto(sourceRepository.findAll());


    }

    @GetMapping("/source/str:{string}")
    public List<SourceDTO> getAllSourcesWithStringInName(@PathVariable CharSequence string) {
        List<Source> sources = sourceRepository.findAll().stream()
                .filter(source -> source.getCode().contains(string))
                .collect(Collectors.toList());
        return sourceConverter.listSourcesToDto(sources);
    }
}
