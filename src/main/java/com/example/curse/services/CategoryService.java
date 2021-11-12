package com.example.curse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curse.entities.Category;
import com.example.curse.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	//metodo que vai retornar todos os usuarios do banco de dados
	public List<Category>findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		
		Optional<Category> obj=repository.findById(id);
		return obj.get();//a opção get vai retornar um objeto do tipo  user que estiver dentro o obj
		
	}
}
