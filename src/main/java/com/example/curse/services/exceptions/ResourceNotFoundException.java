package com.example.curse.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {//essa classe será uma sub classe do RuntimeException é a exception que a ide não obriga a trata

	private static final long serialVersionUID = 1L;
	
	//o construtor recebe um obejeto id
	public ResourceNotFoundException(Object id) {//vai passar o id do objeto que tentou encontrar e não encontrou ai lancha um   ResouceNotFoundException que é recurso não encontrado
		super("Recurso nao encontrado .id "+id);
	}
}
