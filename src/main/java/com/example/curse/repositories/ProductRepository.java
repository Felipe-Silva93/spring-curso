package com.example.curse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curse.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
