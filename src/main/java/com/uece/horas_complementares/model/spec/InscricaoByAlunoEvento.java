package com.uece.horas_complementares.model.spec;

import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.Inscricao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class InscricaoByAlunoEvento implements Specification<Inscricao> {
        private Long alunoMatricula;
        private Long eventoId;

    @Override
    public Predicate toPredicate(Root<Inscricao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
            criteriaBuilder.equal(root.get("id"), eventoId),
            criteriaBuilder.equal(root.get("inscricoes").get("aluno").get("matricula"), alunoMatricula)
        );
        
    }
}
