package com.example.productservice.service.impl;

import com.example.productservice.dto.CollectionDto;
import com.example.productservice.dto.AttributeSaveDto;
import com.example.productservice.dto.CollectionDto;
import com.example.productservice.dto.CollectionSaveDto;
import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.CollectionEntity;
import com.example.productservice.repository.CollectionRepository;
import com.example.productservice.service.AttributeService;
import com.example.productservice.service.CollectionService;
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
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CollectionDto create(CollectionSaveDto dto) {
        var entity = modelMapper.map(dto, CollectionEntity.class);
        entity = collectionRepository.save(entity);
        return modelMapper.map(entity, CollectionDto.class);
    }

    @Override
    @Transactional
    public CollectionDto update(Long id, CollectionSaveDto dto) {
        var entity = collectionRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Collection not found !"));

        entity = collectionRepository.save(entity);
        CollectionDto collectionDto = modelMapper.map(entity, CollectionDto.class);
        return collectionDto;
    }

    @Override
    public Page<CollectionDto> findAll(Pageable pageable) {
        return collectionRepository.findAll(pageable).map(objectEntity -> modelMapper.map(objectEntity, CollectionDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = collectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Collection not found"));
        collectionRepository.delete(entity);
    }

    @Override
    public CollectionDto findById(Long id) {
        return modelMapper.map(collectionRepository.findById(id).orElseThrow(), CollectionDto.class);
    }
}