package com.uece.horas_complementares.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;

@Entity
public class SubCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private int limiteSemestral;
    private int limiteTotal;
    private int limiteAtividade;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private HoraComplementar horaComplementar;

}
