package com.uece.horas_complementares.model.spec;

import org.springframework.data.jpa.domain.Specification;
import com.uece.horas_complementares.model.Presenca;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PresencaByAlunoEvento implements Specification<Presenca> {
    private Long alunoMatricula;
    private Long eventoId;

    @Override
    public Predicate toPredicate(Root<Presenca> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate alunoPredicate = criteriaBuilder.equal(root.get("aluno").get("matricula"), alunoMatricula);
        Predicate eventoPredicate = criteriaBuilder.equal(root.get("evento").get("id"), eventoId);
        return criteriaBuilder.and(alunoPredicate, eventoPredicate);
    }
}