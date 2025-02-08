package com.uece.horas_complementares.model.repository;

import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.user.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByMatriculaProfessor(Professor professor);
    List<Evento> findByMatriculaAluno(Aluno aluno);
}
