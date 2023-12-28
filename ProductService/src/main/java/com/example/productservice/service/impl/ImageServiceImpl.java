package com.example.productservice.service.impl;

import com.example.productservice.entity.ImageEntity;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.model.SearchCategory;
import com.example.productservice.repository.ImageRepository;
import com.example.productservice.service.ImageService;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public ImageEntity create(Long idProduct, ImageEntity imageEntity) {
//        ProductEntity product = productService.findById(idProduct);
//            imageEntity.setProductEntity(product);
//            return imageRepository.save(imageEntity);
        return null;
    }

    @Override
    public ImageEntity update(Long id, ImageEntity imageEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<ImageEntity> findAll(SearchCategory searchCategory, Pageable pageable) {
        return null;
    }

    @Override
    public ImageEntity findById(Long id) {
        return null;
    }

    public ImageEntity updateImage(Long id, ImageEntity imageEntity) {
        imageEntity.setId(id);
        return imageRepository.save(imageEntity);
    }

    public void deleteImage(Long id) {
        ImageEntity imageEntity = imageRepository.getById(id);
        imageRepository.delete(imageEntity);
    }
}
