package com.example.curse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curse.entities.Order;
import com.example.curse.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	//metodo que vai retornar todos os usuarios do banco de dados
	public List<Order>findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		
		Optional<Order> obj=repository.findById(id);
		return obj.get();//a opção get vai retornar um objeto do tipo  user que estiver dentro o obj
		
	}
}
