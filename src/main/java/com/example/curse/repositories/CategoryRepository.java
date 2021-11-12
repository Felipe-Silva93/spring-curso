package com.example.curse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curse.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
