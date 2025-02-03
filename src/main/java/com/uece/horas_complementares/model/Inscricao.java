package com.uece.horas_complementares.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.user.Aluno;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idEvento")
    private Evento idEvento;

    @ManyToOne
    @JoinColumn(name = "aluno_matricula") // Nome da coluna FK
    @JsonIgnore
    private Aluno aluno;

    // Correção: Relação OneToMany (uma inscrição pode ter várias presenças)
    @OneToMany(mappedBy = "idInscricao") // "idInscricao" é o campo em Presenca que referencia Inscricao
    @JsonIgnore
    private List<Presenca> presencas;
}