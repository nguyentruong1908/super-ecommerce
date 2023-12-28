package com.example.productservice.service.impl;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.AttributeValueDto;
import com.example.productservice.dto.SubProductDto;
import com.example.productservice.dto.SubProductSaveDto;
import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.SubProductEntity;
import com.example.productservice.repository.SubProductRepository;
import com.example.productservice.service.CategoryService;
import com.example.productservice.service.SubProductService;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubProductServiceImpl implements SubProductService {

    private final SubProductRepository subProductRepository;
    private final ModelMapper modelMapper;

    @Override
    public SubProductDto create(SubProductSaveDto dto) {
        var entity = modelMapper.map(dto, SubProductEntity.class);
        entity = subProductRepository.save(entity);
        return modelMapper.map(entity, SubProductDto.class);
    }

    @Override
    public SubProductDto update(Long id, SubProductSaveDto dto) {
        var entity = subProductRepository.findById(id)
                .orElseThrow(() -> new ValidationException("SubProduct not found !"));

        SubProductDto subProductDto = modelMapper.map(entity, SubProductDto.class);
        return subProductDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var subProductEntity = subProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubProduct not found"));
        subProductRepository.delete(subProductEntity);
    }

    @Override
    public Page<SubProductDto> findAll(Pageable pageable) {
        return subProductRepository.findAll(pageable).map(objectEntity -> modelMapper.map(objectEntity, SubProductDto.class));
    }

    @Override
    public SubProductDto findById(Long id) {
        return modelMapper.map(subProductRepository.findById(id).orElseThrow(), SubProductDto.class);
    }

}