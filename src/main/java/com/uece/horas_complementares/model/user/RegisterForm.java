package com.uece.horas_complementares.model.user;

import com.uece.horas_complementares.model.Curso;
import com.uece.horas_complementares.model.HoraComplementar;
import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.Presenca;

import java.util.List;

public record RegisterForm(Long matricula,String name, String email, String password, User.TipoUsuario role,Curso curso) {
}


