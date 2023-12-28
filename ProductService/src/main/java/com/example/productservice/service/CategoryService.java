package com.example.productservice.service;

import com.example.productservice.dto.CategorySaveDto;
import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.CategoryEntity;
import com.example.productservice.model.SearchCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
  CategoryDto create(CategorySaveDto dto);
  CategoryDto update(Long id, CategorySaveDto categorySaveDto);
  void delete(Long id);
  Page<CategoryDto> findAll(SearchCategory searchCategory, Pageable pageable);
  CategoryDto findById(Long id);
}
