package com.example.productservice.service.impl;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.AttributeValueDto;
import com.example.productservice.dto.AttributeValueSaveDto;
import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.AttributeValueEntity;
import com.example.productservice.repository.AttributeRepository;
import com.example.productservice.repository.AttributeValueRepository;
import com.example.productservice.service.AttributeValueService;
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
public class AttributeValueServiceImpl implements AttributeValueService {
    private final AttributeValueRepository attributeValueRepository;
    private final AttributeRepository attributeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AttributeValueDto create(AttributeValueSaveDto dto) {
        var entity = modelMapper.map(dto, AttributeValueEntity.class);
        entity = attributeValueRepository.save(entity);
        return modelMapper.map(entity, AttributeValueDto.class);
    }

    @Override
    @Transactional
    public AttributeValueDto update(Long id, AttributeValueSaveDto dto) {
        AttributeValueEntity entity = attributeValueRepository.findById(id)
                .orElseThrow(() -> new ValidationException("AttributeValue not found !"));
        entity.setAttribute(attributeRepository.findById(id).orElseThrow());
        entity = attributeValueRepository.save(entity);
        AttributeValueDto attributeValueDto = modelMapper.map(entity, AttributeValueDto.class);
        return attributeValueDto;
    }

    @Override
    public Page<AttributeValueDto> findAll(Pageable pageable) {
        return attributeValueRepository.findAll(pageable).map(objectEntity -> modelMapper.map(objectEntity, AttributeValueDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AttributeValueEntity attributeValue = attributeValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("AttributeValue not found"));
        attributeValueRepository.delete(attributeValue);
    }

    @Override
    public AttributeValueDto findById(Long id) {
        return modelMapper.map(attributeValueRepository.findById(id).orElseThrow(), AttributeValueDto.class);
    }
}