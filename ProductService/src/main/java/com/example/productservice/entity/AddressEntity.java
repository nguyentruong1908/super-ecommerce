package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nationalName;

    private String province;

    private String district;

    private String wards;

    private String addressDetail;
}