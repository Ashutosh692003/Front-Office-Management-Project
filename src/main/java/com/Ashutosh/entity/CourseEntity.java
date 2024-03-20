package com.Ashutosh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Courses")
@Data
public class CourseEntity {
	     
	    @Id
	    @GeneratedValue
	 private Integer id;
	 private String name;

}
