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
//		Professor prof = new Professor(
//				1234589L,
//				"prof thelmo",
//				"thelmo@uece.br",
//				true,
//				"senha123",
//				"tokenXYZ2y",
//				User.TipoUsuario.PROFESSOR,
//				null,
//				false
//		);
//		String encryptedPassword = new BCryptPasswordEncoder().encode(prof.getPassword());
//		prof.setSenha(encryptedPassword);
//		userRepository.save(prof);
//	}
}
