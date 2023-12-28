package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @OneToOne
    @JoinColumn(name = "category")
    private CategoryEntity category;

    private int price;

    private String description;

    private Double discount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SubProductEntity> subProduct;

    @ManyToMany
    @JoinTable(name = "product_collection", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private List<CollectionEntity> collection;

}
