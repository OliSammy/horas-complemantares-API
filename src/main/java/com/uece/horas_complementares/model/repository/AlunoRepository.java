package com.uece.horas_complementares.model.repository;

import com.uece.horas_complementares.model.user.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, AlunoRepositoryQueries{
    Optional<Aluno> findByMatricula(Long matricula);
    void deleteByMatricula(Long matricula);
}
