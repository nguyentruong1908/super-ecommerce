package com.example.productservice.entity;

import com.example.productservice.model.Gender;
import com.example.productservice.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role roles;

    private String firstName;

    private String lastName;

    private String phone;

    @OneToOne
    @JoinColumn(name = "address")
    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String avatarUrl;
}