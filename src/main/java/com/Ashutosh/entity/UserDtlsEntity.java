package com.Ashutosh.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="staffDetails")
@Getter
@Setter

public class UserDtlsEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
          private Integer id;
          private String name;
          private String email;
          private String ph_number;
          private String password;
          private String acc_status;
   @OneToMany(mappedBy="user" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  
          private List<StudentEnqEntity> enquiries;
} 
