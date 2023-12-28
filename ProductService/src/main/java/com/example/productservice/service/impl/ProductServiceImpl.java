package com.example.productservice.service.impl;

import com.example.productservice.dto.AttributeDto;
import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.ProductSaveDto;
import com.example.productservice.entity.*;
import com.example.productservice.model.SearchProduct;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.CategoryService;
import com.example.productservice.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public ProductDto create(ProductSaveDto productSaveDto) {
        var entity = modelMapper.map(productSaveDto, ProductEntity.class);
        entity = productRepository.save(entity);
        return modelMapper.map(entity, ProductDto.class);
    }

    @Override
    @Transactional
    public ProductDto update(Long id, ProductSaveDto productSaveDto) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Category not found !"));
        entity.setProductName(productSaveDto.getProductName());
        CategoryEntity categoryEntity = modelMapper.map(categoryService.findById(productSaveDto.getCategory().getId()), CategoryEntity.class);
        entity.setCategory(categoryEntity);
        entity.setPrice(productSaveDto.getPrice());
        entity.setDescription(productSaveDto.getDescription());
        entity.setDiscount(productSaveDto.getDiscount());
        entity = productRepository.save(entity);
        ProductDto productDto = modelMapper.map(entity, ProductDto.class);
        return productDto;
    }

    @Override
    public Page<ProductDto> findAll(Map<String, String> attributeFilter, Pageable pageable) {
        Specification<ProductEntity> specification = (root, query, criteriaBuilder) -> criteriaBuilder.and();

        if (attributeFilter.get("searchQuery") != null) {
            specification = ((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("productName"), "%" + attributeFilter.get("searchQuery") + "%"));
            specification = specification.or(((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("description"), "%" + attributeFilter.get("searchQuery") + "%")));
        }

        if (attributeFilter.get("category") != null) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Join<CategoryEntity, ProductEntity> category = root.join("category");
                return criteriaBuilder.equal(category.get("categoryName"), attributeFilter.get("category"));
            });
        }

        if (attributeFilter.get("categoryParent") != null) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Join<CategoryEntity, ProductEntity> category = root.join("category");
                Join<CategoryEntity, CategoryEntity> categoryParent = root.join("categoryParent");
                return criteriaBuilder.equal(categoryParent.get("id"), attributeFilter.get("categoryParent"));
            });
        }

        if (attributeFilter.get("priceFrom") != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), attributeFilter.get("priceFrom"))));
        }

        if (attributeFilter.get("priceTo") != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), attributeFilter.get("priceTo"))));
        }

        if (attributeFilter.get("discount") != null) {
            specification = specification.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.lessThanOrEqualTo(root.get("discount"), attributeFilter.get("discount"))));
        }

        if (!attributeFilter.isEmpty()) {
            for (Map.Entry<String, String> entry : attributeFilter.entrySet()) {
                if (entry.getKey() == "searchQuery" ||
                        entry.getKey().equals("category") ||
                        entry.getKey().equals("categoryParent") ||
                        entry.getKey().equals("priceFrom") ||
                        entry.getKey().equals("priceTo") ||
                        entry.getKey().equals("discount")) {
                    continue;
                }
                specification = specification.and((root, query, criteriaBuilder) -> {
                    Join<SubProductEntity, ProductEntity> subProduct = root.join("subProduct");
                    Join<AttributeValueEntity, SubProductEntity> attributeValue = subProduct.join("attributeValue");
                    Join<AttributeEntity, AttributeValueEntity> attribute = attributeValue.join("attribute");
                    return criteriaBuilder.and(criteriaBuilder.equal(attribute.get("name"), entry.getKey()),
                            criteriaBuilder.equal(attributeValue.get("value"), entry.getValue()));
                });
            }
        }

        return productRepository.findAll(specification, pageable).map(objectEntity -> modelMapper.map(objectEntity, ProductDto.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProductEntity attributeEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.delete(attributeEntity);
    }

    @Override
    public ProductDto findById(Long id) {
        return modelMapper.map(productRepository.findById(id).orElseThrow(), ProductDto.class);
    }

}