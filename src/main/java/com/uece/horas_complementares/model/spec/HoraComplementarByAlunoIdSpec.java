package com.uece.horas_complementares.model.spec;

import com.uece.horas_complementares.controller.HorasComplementares;
import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.HoraComplementar;
import com.uece.horas_complementares.model.user.User;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class HoraComplementarByAlunoIdSpec implements Specification<HoraComplementar> {
    private Long alunoMatricula;

    @Override
    public Predicate toPredicate(Root<HoraComplementar> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("Aluno").get("matricula"), alunoMatricula);
    }

}
