package com.uece.horas_complementares.model.spec;

import org.springframework.data.jpa.domain.Specification;
import com.uece.horas_complementares.model.Evento;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EventoByIdSpec implements Specification<Evento> {
    private Long id;

    @Override
    public Predicate toPredicate(Root<Evento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("id"), id);
    }

}
