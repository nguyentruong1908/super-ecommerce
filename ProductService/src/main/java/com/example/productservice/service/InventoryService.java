package com.example.productservice.service;

import com.example.productservice.dto.InventoryDto;
import com.example.productservice.dto.InventorySaveDto;
import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductSaveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface InventoryService {
    InventoryDto create(InventorySaveDto inventorySaveDto);
    InventoryDto update(Long id, InventorySaveDto inventorySaveDto);
    void delete(Long id);
    Page<InventoryDto> findAll(Pageable pageable);
    InventoryDto findById(Long id);
}
