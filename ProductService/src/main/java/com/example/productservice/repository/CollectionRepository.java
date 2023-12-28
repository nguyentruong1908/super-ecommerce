package com.example.productservice.repository;

import com.example.productservice.entity.AttributeEntity;
import com.example.productservice.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long>, JpaSpecificationExecutor<CollectionEntity> {
}
