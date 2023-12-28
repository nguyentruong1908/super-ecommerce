package com.example.productservice.controller;

import com.example.productservice.dto.AttributeValueDto;
import com.example.productservice.dto.AttributeValueSaveDto;
import com.example.productservice.service.AttributeValueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attributevalue")
@RequiredArgsConstructor
public class AttributeValueController {

    private final AttributeValueService attributeValueService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<AttributeValueDto> create(@Valid @RequestBody AttributeValueSaveDto dto) {
        return ResponseEntity.ok(attributeValueService.create(dto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AttributeValueDto> update(@Valid @RequestBody AttributeValueSaveDto dto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(attributeValueService.update(id, dto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        attributeValueService.delete(id);
    }

    @GetMapping
    public Page<AttributeValueDto> findAll(Pageable pageable) {
        return attributeValueService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public AttributeValueDto findById(@PathVariable("id") Long id) {
        return attributeValueService.findById(id);
    }

}
