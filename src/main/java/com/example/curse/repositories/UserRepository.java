package com.example.curse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curse.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
