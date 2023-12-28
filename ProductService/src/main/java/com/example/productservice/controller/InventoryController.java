package com.example.productservice.controller;

import com.example.productservice.dto.InventoryDto;
import com.example.productservice.dto.InventorySaveDto;
import com.example.productservice.dto.SubProductDto;
import com.example.productservice.dto.SubProductSaveDto;
import com.example.productservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<InventoryDto> create(@Valid @RequestBody InventorySaveDto inventorySaveDto) {
        return ResponseEntity.ok(inventoryService.create(inventorySaveDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@Valid @RequestBody InventorySaveDto inventorySaveDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(inventoryService.update(id, inventorySaveDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        inventoryService.delete(id);
    }

    @GetMapping()
    public Page<InventoryDto> findAll(Pageable pageable) {
        return inventoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public InventoryDto findById(@PathVariable("id") Long id) {
        return inventoryService.findById(id);
    }

}
