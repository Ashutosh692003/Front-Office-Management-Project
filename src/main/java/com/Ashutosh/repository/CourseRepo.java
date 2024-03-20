package com.Ashutosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ashutosh.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity,Integer> {

}
