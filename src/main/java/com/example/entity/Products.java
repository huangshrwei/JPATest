package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Table(name = "products")
@Data
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @Column(name = "category")
    private String category;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false, nullable = false)
    private Date creationDate;   

    @Column(name = "creation_by")
    private Integer creationBy;
    
    @UpdateTimestamp
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;     
    
    
}