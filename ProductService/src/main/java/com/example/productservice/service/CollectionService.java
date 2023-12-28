package com.example.productservice.service;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.AttributeSaveDto;
import com.example.productservice.dto.CollectionDto;
import com.example.productservice.dto.CollectionSaveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CollectionService {
  CollectionDto create(CollectionSaveDto dto);
  CollectionDto update(Long id, CollectionSaveDto collectionSaveDto);
  void delete(Long id);
  Page<CollectionDto> findAll(Pageable pageable);
  CollectionDto findById(Long id);
}
