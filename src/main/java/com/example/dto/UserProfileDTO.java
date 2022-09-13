package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserProfileDTO {

    private Long userId;

    private String userName;

    private String userPassword;

    private Date creationDate;
    
    private Date updatedDate;	
	
}
