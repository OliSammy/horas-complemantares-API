package com.uece.horas_complementares;

import com.uece.horas_complementares.model.repository.UserRepository;
import com.uece.horas_complementares.model.user.Coordenador;
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
//		Coordenador coord = new Coordenador(
//				123458L,
//				"NEGREIROS",
//				"neg@uece.br",
//				true,
//				"senha123",
//				"tokenXYZy",
//				null, // Curso (pode ser null)
//				true
//		);
//		String encryptedPassword = new BCryptPasswordEncoder().encode(coord.getPassword());
//		coord.setSenha(encryptedPassword);
//		userRepository.save(coord);
//	}
}
