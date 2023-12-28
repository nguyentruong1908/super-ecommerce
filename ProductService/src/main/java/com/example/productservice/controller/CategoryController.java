package com.example.productservice.controller;

import com.example.productservice.dto.CategorySaveDto;
import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.CategoryEntity;
import com.example.productservice.model.SearchCategory;
import com.example.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@Valid @NotNull @RequestBody CategorySaveDto createDto) {
        return ResponseEntity.ok(categoryService.create(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@Valid @NotNull @RequestBody CategorySaveDto categorySaveDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.update(id, categorySaveDto));
    }

    @GetMapping
    public Page<CategoryDto> findAll(SearchCategory searchCategory, Pageable pageable) {
        return categoryService.findAll(searchCategory, pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
       categoryService.delete(id);
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

}
