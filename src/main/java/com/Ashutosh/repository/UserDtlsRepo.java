package com.Ashutosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ashutosh.entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity,Integer> {
             
	 public UserDtlsEntity findByEmail(String email);
}
