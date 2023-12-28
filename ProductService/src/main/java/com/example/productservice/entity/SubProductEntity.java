package com.example.productservice.entity;

import com.example.productservice.model.AttributeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sub_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubProductEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "subProduct")
    private List<ImageEntity> image;

    @OneToMany(mappedBy = "subProduct", cascade = CascadeType.ALL)
    private List<AttributeValueEntity> attributeValue;

    @OneToOne(mappedBy = "subProduct")
    private InventoryEntity inventory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
