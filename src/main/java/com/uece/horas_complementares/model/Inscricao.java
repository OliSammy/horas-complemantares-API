package com.uece.horas_complementares.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.List;

@Entity
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idEvento") // Fk para tabela Evento
    private Evento idEvento;

    @ManyToOne
    @JoinColumn(name = "matriculaAluno") // Fk para tabela Aluno
    private Aluno matriculaAluno;

    @OneToOne(mappedBy = "idInscricao") // Relacionamento com a tabela Presença
    @JoinColumn(name = "idInscricao")
    private List<Presenca> presencas;
}
