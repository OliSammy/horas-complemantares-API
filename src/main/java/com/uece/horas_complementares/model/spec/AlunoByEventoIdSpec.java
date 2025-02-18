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
public class AlunoByEventoIdSpec implements Specification<User> {
    private Long idEvento;
    private Long idProfessor;


    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate evento = criteriaBuilder.equal(root.get("inscricoes").get("idEvento").get("id"), idEvento);
        Predicate professor = criteriaBuilder.equal(root.get("inscricoes").get("idEvento").get("matriculaProfessor").get("matricula"), idProfessor);
        return criteriaBuilder.and(evento, professor);


    }

}
