package com.uece.horas_complementares.model.user;

import com.uece.horas_complementares.model.Curso;


public record RegisterForm(Long matricula,String name, String email, String password, User.TipoUsuario role,Curso curso) {
}


