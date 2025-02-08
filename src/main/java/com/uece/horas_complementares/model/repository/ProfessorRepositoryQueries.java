package com.uece.horas_complementares.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uece.horas_complementares.model.Evento;

public interface ProfessorRepositoryQueries {
    @Query("SELECT e FROM Evento e WHERE e.professor.id = :professorId")
    List<Evento> findEventosByProfessorId(@Param("professorId") Long professorId);
}
