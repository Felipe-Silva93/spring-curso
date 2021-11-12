package com.example.curse.services.exceptions;

public class DatabaseException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg) {//recebendo uma string msg 
		super(msg);//chamando o construtor da super classe
	}

}
