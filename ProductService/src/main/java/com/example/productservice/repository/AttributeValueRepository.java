package com.example.productservice.repository;

import com.example.productservice.entity.AttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeValueRepository  extends JpaRepository<AttributeValueEntity, Long>, JpaSpecificationExecutor<AttributeValueEntity> {
}
