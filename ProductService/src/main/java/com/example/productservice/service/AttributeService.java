package com.example.productservice.service;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.AttributeSaveDto;
import com.example.productservice.dto.CategoryDto;
import com.example.productservice.dto.CategorySaveDto;
import com.example.productservice.model.SearchCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AttributeService {
  AttributeDto create(AttributeSaveDto dto);
  AttributeDto update(Long id, AttributeSaveDto categorySaveDto);
  void delete(Long id);
  Page<AttributeDto> findAll(Pageable pageable);
  AttributeDto findById(Long id);
}
