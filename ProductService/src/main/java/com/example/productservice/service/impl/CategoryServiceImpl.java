package com.example.productservice.service.impl;

import com.example.productservice.dto.CategorySaveDto;
import com.example.productservice.dto.CategoryDto;
import com.example.productservice.entity.CategoryEntity;
import com.example.productservice.model.SearchCategory;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CategoryDto create(CategorySaveDto categoryDto) {
        var entity = modelMapper.map(categoryDto, CategoryEntity.class);
        entity = categoryRepository.save(entity);
        return modelMapper.map(entity, CategoryDto.class);
    }

    @Override
    @Transactional
    public CategoryDto update(Long id, CategorySaveDto categorySaveDto) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Category not found !"));
        entity.setCategoryName(categorySaveDto.getCategoryName());
        entity.setCategoryParent(categoryRepository.findById(categorySaveDto.getParentId()).orElseThrow(() -> new ValidationException("Category parent not found !")));
        entity = categoryRepository.save(entity);
        CategoryDto categoryDto = modelMapper.map(entity, CategoryDto.class);
        categoryDto.setParentId(entity.getCategoryParent().getId());
        return categoryDto;
    }

    @Override
    public Page<CategoryDto> findAll(SearchCategory searchCategory, Pageable pageable) {
        Specification<CategoryEntity> specification = (root, query, criteriaBuilder) -> criteriaBuilder.and();

        if (searchCategory.getParent() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Join<CategoryEntity, CategoryEntity> category = root.join("categoryParent");
                return criteriaBuilder.equal(category.get("id"), searchCategory.getParent());
            });
        }
        return categoryRepository.findAll(specification, pageable).map(objectEntity -> modelMapper.map(objectEntity, CategoryDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        categoryRepository.delete(categoryEntity);
    }

    @Override
    public CategoryDto findById(Long id) {
        return modelMapper.map(categoryRepository.findById(id).orElseThrow(), CategoryDto.class);
    }
}