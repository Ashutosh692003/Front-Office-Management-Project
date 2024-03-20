package com.Ashutosh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ashutosh.entity.StudentEnqEntity;
import com.Ashutosh.entity.UserDtlsEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity,Integer> {
	

}
