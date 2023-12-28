package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductSaveDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.model.SearchCategory;
import com.example.productservice.model.SearchProduct;
import com.example.productservice.service.ProductService;
import com.netflix.discovery.EurekaClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductSaveDto productSaveDto) {
        return ResponseEntity.ok(productService.create(productSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@Valid @RequestBody ProductSaveDto productSaveDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.update(id, productSaveDto));
    }

    @GetMapping()
    public Page<ProductDto> findAll(@RequestParam Map<String, String> attributeFilter, Pageable pageable) {
        return productService.findAll(attributeFilter, pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @GetMapping()
    public Page<ProductDto> Search(@RequestParam Map<String, String> attributeFilter, Pageable pageable) {
        return productService.findAll(attributeFilter, pageable);
    }
}
