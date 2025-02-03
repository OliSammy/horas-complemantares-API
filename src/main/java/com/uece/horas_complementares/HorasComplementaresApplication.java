package com.uece.horas_complementares;

import com.uece.horas_complementares.model.Aluno;
import com.uece.horas_complementares.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.uece.horas_complementares.model")
public class HorasComplementaresApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorasComplementaresApplication.class, args);
	}


//	@PostConstruct
//	public void insertUser(){
//		Aluno newAluno = new User("aluno", "123456", "gabriel.brasil@aluno.uece.br", true, "gabriel", 123, "666", false, null);
//	}
}
