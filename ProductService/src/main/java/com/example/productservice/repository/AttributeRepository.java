package com.example.productservice.repository;

import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.AttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Long>, JpaSpecificationExecutor<AttributeEntity> {
}
