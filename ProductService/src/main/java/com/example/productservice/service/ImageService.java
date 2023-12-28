package com.example.productservice.service;

import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.ImageEntity;
import com.example.productservice.model.SearchCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {
  ImageEntity create(Long idProduct, ImageEntity imageEntity);
  ImageEntity update(Long id, ImageEntity imageEntity);
  void delete(Long id);
  Page<ImageEntity> findAll(SearchCategory searchCategory, Pageable pageable);
  ImageEntity findById(Long id);
}
