package com.example.productservice.service.impl;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.AttributeSaveDto;
import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.CategoryEntity;
import com.example.productservice.repository.AttributeRepository;
import com.example.productservice.service.AttributeService;
import jakarta.persistence.criteria.Join;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AttributeDto create(AttributeSaveDto dto) {
        var entity = modelMapper.map(dto, AttributeEntity.class);
        entity = attributeRepository.save(entity);
        return modelMapper.map(entity, AttributeDto.class);
    }

    @Override
    @Transactional
    public AttributeDto update(Long id, AttributeSaveDto dto) {
        var entity = attributeRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Attribute not found !"));
        entity.setName(dto.getName());
        entity.setAttributeType(dto.getAttributeType());
        entity = attributeRepository.save(entity);
        AttributeDto categoryDto = modelMapper.map(entity, AttributeDto.class);
        return categoryDto;
    }

    @Override
    public Page<AttributeDto> findAll(Pageable pageable) {
        return attributeRepository.findAll(pageable).map(objectEntity -> modelMapper.map(objectEntity, AttributeDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var attributeEntity = attributeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attribute not found"));
        attributeRepository.delete(attributeEntity);
    }

    @Override
    public AttributeDto findById(Long id) {
        return modelMapper.map(attributeRepository.findById(id).orElseThrow(), AttributeDto.class);
    }
}