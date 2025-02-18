package com.uece.horas_complementares.model.repository;


import com.uece.horas_complementares.controller.HorasComplementares;
import com.uece.horas_complementares.model.HoraComplementar;
import com.uece.horas_complementares.model.Inscricao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HorasComplementaresRepository extends JpaRepository<HoraComplementar, Long>, JpaSpecificationExecutor<HoraComplementar> {

}
