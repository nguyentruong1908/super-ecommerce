package com.example.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

  @CreatedBy
  @Column(name = "created_by")
  private Long createdBy;

//    @JoinColumn(name = "created_by", insertable = false, updatable = false)
//    @ManyToOne
//    private UserEntity createdByUser;

  @CreatedDate
  @Temporal(TIMESTAMP)
  private LocalDateTime createdDate;

  @LastModifiedBy
  @Column(name = "last_modified_by")
  private Long lastModifiedBy;

//    @JoinColumn(name = "last_modified_by", insertable = false, updatable = false)
//    @ManyToOne
//    private UserEntity lastModifiedByUser;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime lastModifiedDate;

}