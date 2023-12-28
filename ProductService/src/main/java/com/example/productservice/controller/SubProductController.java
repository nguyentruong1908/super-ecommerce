package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductSaveDto;
import com.example.productservice.dto.SubProductDto;
import com.example.productservice.dto.SubProductSaveDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.model.SearchProduct;
import com.example.productservice.service.ProductService;
import com.example.productservice.service.SubProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/subproduct")
@RequiredArgsConstructor
public class SubProductController {

    private final SubProductService subProductService;

    @PostMapping()
    public ResponseEntity<SubProductDto> create(@Valid @RequestBody SubProductSaveDto subProductSaveDto) {
        return ResponseEntity.ok(subProductService.create(subProductSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubProductDto> update(@Valid @RequestBody SubProductSaveDto subProductSaveDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(subProductService.update(id, subProductSaveDto));
    }

    @GetMapping()
    public Page<SubProductDto> findAll(Pageable pageable) {
        return subProductService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        subProductService.delete(id);
    }

    @GetMapping("/{id}")
    public SubProductDto findById(@PathVariable("id") Long id) {
        return subProductService.findById(id);
    }

}
