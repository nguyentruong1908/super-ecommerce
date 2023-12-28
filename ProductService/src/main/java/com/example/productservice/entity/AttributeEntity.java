package com.example.productservice.entity;

import com.example.productservice.model.AttributeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AttributeType attributeType;
}
