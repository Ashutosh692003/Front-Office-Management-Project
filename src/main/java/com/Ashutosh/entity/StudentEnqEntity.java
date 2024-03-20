package com.Ashutosh.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="AIT_STUDENT_ENQUIRY")
public class StudentEnqEntity {
	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Integer enqId;
     private String name;
     private String classMode;
     private String course;
     private Long number;
     private String enqStatus;
      @UpdateTimestamp
     private LocalDate lastUpdated;
      @CreationTimestamp
      @Column( nullable = false, updatable = false)
     private LocalDate dateCreated;
      
      @ManyToOne
         @JoinColumn(name ="id")
     
      private UserDtlsEntity user;
     
}
