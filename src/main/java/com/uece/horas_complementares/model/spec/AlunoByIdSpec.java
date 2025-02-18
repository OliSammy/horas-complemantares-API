package com.uece.horas_complementares.model.spec;

import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.user.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@AllArgsConstructor
public class AlunoByIdSpec implements Specification<User> {
    private Long alunoMatricula;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("matricula"), alunoMatricula);
    }

}
