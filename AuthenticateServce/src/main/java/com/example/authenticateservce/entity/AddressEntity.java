package com.example.authenticateservce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "address")
@Data
@EntityListeners(AuditingEntityListener.class)
public class AddressEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nationalName;

    private String province;

    private String district;

    private String wards;

    private String addressDetail;
}