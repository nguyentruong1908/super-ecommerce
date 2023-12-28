package com.example.productservice.service;

import com.example.productservice.dto.AttributeValueDto;
import com.example.productservice.dto.AttributeValueSaveDto;
import com.example.productservice.dto.CategoryDto;
import com.example.productservice.dto.CategorySaveDto;
import com.example.productservice.model.SearchCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AttributeValueService {
  AttributeValueDto create(AttributeValueSaveDto dto);
  AttributeValueDto update(Long id, AttributeValueSaveDto dto);
  void delete(Long id);
  Page<AttributeValueDto> findAll(Pageable pageable);
  AttributeValueDto findById(Long id);
}
