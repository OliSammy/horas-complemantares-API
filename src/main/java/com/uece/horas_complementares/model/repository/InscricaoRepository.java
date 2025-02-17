package com.uece.horas_complementares.model.repository;

import com.uece.horas_complementares.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long>,JpaSpecificationExecutor<Inscricao> {
    List<Inscricao> findByAlunoMatricula(Long alunoMatricula);
    List<Inscricao> findByIdEvento_IdAndAluno_Matricula(Long idEvento, Long matriculaAluno);
}
