package com.informatorio.trabajofinal.controller;

import com.informatorio.trabajofinal.converter.SourceConverter;
import com.informatorio.trabajofinal.domain.Article;
import com.informatorio.trabajofinal.domain.Source;
import com.informatorio.trabajofinal.dto.SourceDTO;
import com.informatorio.trabajofinal.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    public ResponseEntity<SourceDTO> createSource(@RequestBody @Valid SourceDTO sourceDTO) {
        Source source = sourceConverter.toEntity(sourceDTO);
        sourceRepository.save(source);
        return new ResponseEntity<>(sourceConverter.toDto(source), HttpStatus.CREATED);
    }

    @DeleteMapping("/source/{id}")
    public ResponseEntity<?> deleteSource(@PathVariable Integer id) {
        Source capture = sourceRepository.findById(id).orElse(null);
        if (capture != null) {
            sourceRepository.deleteById(id);
            return new ResponseEntity<>(sourceConverter.toDto(capture), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Source inexistente", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/source/{id}")
    public ResponseEntity<?> updateSource(@PathVariable Integer id, @RequestBody @Valid SourceDTO sourceDTO) {
        Source source = sourceRepository.findById(id).orElse(null);
        if (source != null) {
            source.setName(sourceDTO.getName());
            source.setCode(sourceDTO.getName());
            sourceRepository.save(source);
            return new ResponseEntity<>(sourceConverter.toDto(source), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Source no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/source")
    public ResponseEntity<List<SourceDTO>> getAllSources() {
        return new ResponseEntity<>(sourceConverter.listSourcesToDto(sourceRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "/source", params = {"string"})
    public ResponseEntity<List<SourceDTO>> getAllSourcesContainsStringInName(@RequestParam String string) {
        List<Source> sources = sourceRepository.findAll().stream()
                .filter(source -> source.getCode().contains(string.toLowerCase()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sourceConverter.listSourcesToDto(sources), HttpStatus.OK);
    }

}