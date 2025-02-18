package com.uece.horas_complementares.model.spec;

<<<<<<< HEAD
=======
import com.uece.horas_complementares.controller.HorasComplementaresController;
>>>>>>> 3b3c8e235660193b7ac3a5b2cb5028c20f3f13d7
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
        return criteriaBuilder.equal(root.get("aluno").get("matricula"), alunoMatricula);
    }

}
