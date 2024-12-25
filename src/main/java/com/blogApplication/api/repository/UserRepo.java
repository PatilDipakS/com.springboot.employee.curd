package com.blogApplication.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApplication.api.entities.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

}
