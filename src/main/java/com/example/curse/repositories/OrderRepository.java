package com.example.curse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curse.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
