package com.uece.horas_complementares.model.exception.auth;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(String mensagem){
        super(mensagem);
    }
}
