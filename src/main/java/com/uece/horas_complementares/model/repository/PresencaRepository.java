package com.uece.horas_complementares.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uece.horas_complementares.model.Presenca;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    Presenca findByIdInscricao_IdEvento_IdAndAluno_Matricula(Long idEvento, Long matriculaAluno);
}
