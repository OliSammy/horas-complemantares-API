package com.uece.horas_complementares.model.exception.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String mensagem){super(mensagem);}
}
