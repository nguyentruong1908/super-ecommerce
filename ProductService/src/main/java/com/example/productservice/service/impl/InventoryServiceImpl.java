package com.example.productservice.service.impl;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.AttributeSaveDto;
import com.example.productservice.dto.InventoryDto;
import com.example.productservice.dto.InventorySaveDto;
import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.InventoryEntity;
import com.example.productservice.repository.InventoryRepository;
import com.example.productservice.service.InventoryService;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public InventoryDto create(InventorySaveDto dto) {
        var entity = modelMapper.map(dto, InventoryEntity.class);
        entity = inventoryRepository.save(entity);
        return modelMapper.map(entity, InventoryDto.class);
    }

    @Override
    @Transactional
    public InventoryDto update(Long id, InventorySaveDto dto) {
        InventoryEntity entity = inventoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Inventory not found !"));
        entity.setQuantity(dto.getQuantity());
        entity = inventoryRepository.save(entity);
        InventoryDto inventoryDto = modelMapper.map(entity, InventoryDto.class);
        return inventoryDto;
    }

    @Override
    public Page<InventoryDto> findAll(Pageable pageable) {
        return inventoryRepository.findAll(pageable).map(objectEntity -> modelMapper.map(objectEntity, InventoryDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        InventoryEntity entity = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));
        inventoryRepository.delete(entity);
    }

    @Override
    public InventoryDto findById(Long id) {
        return modelMapper.map(inventoryRepository.findById(id).orElseThrow(), InventoryDto.class);
    }
}