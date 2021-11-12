package com.example.curse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curse.entities.Product;
import com.example.curse.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	//metodo que vai retornar todos os usuarios do banco de dados
	public List<Product>findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		
		Optional<Product> obj=repository.findById(id);
		return obj.get();//a opção get vai retornar um objeto do tipo  user que estiver dentro o obj
		
	}
}
