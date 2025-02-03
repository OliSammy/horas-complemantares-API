package com.uece.horas_complementares;

import com.uece.horas_complementares.model.repository.UserRepository;
import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HorasComplementaresApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorasComplementaresApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

//	@PostConstruct
//	public void insertUser(){
//
//		Professor aluno = new Professor(
//				123456L,                  // matricula (Long)
//				"Gabriel coord",               // nome
//				"gabriel.bra@aluno.uece.br", // email
//				true,                    // emailValidado
//				"senha123",              // senha
//				"tokenXYZy",              // token
//				User.TipoUsuario.COORDENADOR,  // tipoUsuario (discriminador)
//				null,             // curso (objeto Curso)
//				true
//		);
//		String encryptedPassword = new BCryptPasswordEncoder().encode(aluno.getPassword());
//		aluno.setSenha(encryptedPassword);
//		userRepository.save(aluno);
//	}
}
