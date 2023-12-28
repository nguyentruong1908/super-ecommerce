package com.example.productservice.controller;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.CollectionDto;
import com.example.productservice.dto.CollectionSaveDto;
import com.example.productservice.service.CollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CollectionDto> create(@Valid @RequestBody CollectionSaveDto dto) {
        return ResponseEntity.ok(collectionService.create(dto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CollectionDto> update(@Valid @RequestBody CollectionSaveDto dto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(collectionService.update(id, dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public Page<CollectionDto> findAll(Pageable pageable) {
        return collectionService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
       collectionService.delete(id);
    }

    @GetMapping("/{id}")
    public CollectionDto findById(@PathVariable("id") Long id) {
        return collectionService.findById(id);
    }

}
