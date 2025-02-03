package com.uece.horas_complementares.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.user.Professor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String banner;//gabriel como bota isso como imagem ?S KSKSKSKSK
    private String tipoHorasComplementares;
    private String dataInicial;
    private String dataFinal;
    private String horarioInicial;
    private String horarioFinal;
    private int limiteDedescrição;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idCurso") // Fk para tabela Curso
    @JsonIgnore
    private Curso idCurso;

    @ManyToOne
    @JoinColumn(name = "matriculaProfessor") // Fk para tabela Professor
    @JsonIgnore
    private Professor matriculaProfessor;

    @OneToMany(mappedBy = "idEvento") // Relacionamento com a tabela Inscrição
    @JsonIgnore
    private List<Inscricao> inscricoes;


}