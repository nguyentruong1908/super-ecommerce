package com.example.productservice.repository;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.entity.SubProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProductRepository extends JpaRepository<SubProductEntity, Long>, JpaSpecificationExecutor<SubProductEntity> {
}