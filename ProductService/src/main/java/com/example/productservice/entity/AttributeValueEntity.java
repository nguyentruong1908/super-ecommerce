package com.example.productservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attribute_value")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private AttributeEntity attribute;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sub_product_id")
    private SubProductEntity subProduct;

    private String value;
}
