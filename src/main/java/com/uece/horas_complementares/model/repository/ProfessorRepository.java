package com.uece.horas_complementares.model.repository;

import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.user.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
 
    Professor findByMatricula(Long matricula);
}
