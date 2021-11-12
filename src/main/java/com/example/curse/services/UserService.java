package com.example.curse.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.curse.entities.User;
import com.example.curse.repositories.UserRepository;
import com.example.curse.services.exceptions.DatabaseException;
import com.example.curse.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	//metodo que vai retornar todos os usuarios do banco de dados
	public List<User>findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		
		Optional<User> obj=repository.findById(id);
			//.orElseThrow tenta dar um get se não tiver usuario eu vou lançar uma exceção ->
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
		
		
		// obj.get(); a opção get vai retornar um objeto do tipo  user que estiver dentro o obj << opção substituida
	}
	//salvar no banco de dados os dados do usuario
	public User insert(User obj) {//inserir no banco um novo obejeto do tipo User
		return repository.save(obj);
	}
	
	public void delete(Long id) {
			try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e){//o tipo mais generico de exceptio por que qual quer exception vai combinar com ela
			throw new ResourceNotFoundException(id);//se caso não existir lançar a exception com o ressultado 404
		
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}	
	public User update(Long id, User obj) {
		//getById()//getOne(id)(getById() foi trocado getOne não fuinciona mais a função dele e não vai jogar as informaçoes na banco apenas vai monitorar as mesmas e permitir trabalhas com elas//prepara o obj e deixa monitorado para depois jogar pára o banco
		try {
			User entity = repository.getById(id);
			updateData(entity,obj);//atualizar os dados do entity baseados nas informaçoes que chegou do obj
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
		
			throw new ResourceNotFoundException(id);
			}
		}

	private void updateData(User entity, User obj) {//atualizar os dados do entity com os dados que chegou do obj//naõdeixando atualizar nem o id e nem a senha
		
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
