package com.example.entity;

import java.util.Date;
//import java.util.Set;
import java.util.Set;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "user_profile")
@Entity
@Data
public class UserProfileToDetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)		
    @Column(name = "user_id")	
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    private Date creationDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate; 
    
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id") 
    @EqualsAndHashCode.Exclude
    private Set<UserDetailToProfile> userDetailToProfile;   
    

}