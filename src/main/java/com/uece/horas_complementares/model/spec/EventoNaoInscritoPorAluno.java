package com.uece.horas_complementares.model.spec;
import org.springframework.data.jpa.domain.Specification;
import com.uece.horas_complementares.model.Evento;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EventoNaoInscritoPorAluno implements Specification<Evento> {
    private Long alunoMatricula;

    @Override
    public Predicate toPredicate(Root<Evento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // LEFT JOIN para pegar eventos com ou sem inscrições
        Join<Object, Object> inscricaoJoin = root.join("inscricoes", JoinType.LEFT);
        
        // Filtrar os eventos onde a inscrição do aluno NÃO EXISTE
        return criteriaBuilder.or(
            criteriaBuilder.isNull(inscricaoJoin.get("aluno").get("matricula")),  // Eventos sem inscrições
            criteriaBuilder.notEqual(inscricaoJoin.get("aluno").get("matricula"), alunoMatricula) // Eventos onde o aluno não está inscrito
        );
    }
}
