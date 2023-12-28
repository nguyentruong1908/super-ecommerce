package com.example.productservice.controller;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.AttributeSaveDto;
import com.example.productservice.service.AttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageProductController {

    private final AttributeService attributeService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<AttributeDto> create(@Valid @RequestBody AttributeSaveDto dto) {
        return ResponseEntity.ok(attributeService.create(dto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AttributeDto> update(@Valid @RequestBody AttributeSaveDto dto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(attributeService.update(id, dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public Page<AttributeDto> findAll(Pageable pageable) {
        return attributeService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
       attributeService.delete(id);
    }

    @GetMapping("/{id}")
    public AttributeDto findById(@PathVariable("id") Long id) {
        return attributeService.findById(id);
    }

}
