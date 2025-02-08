package com.uece.horas_complementares.model.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {
    private Long id;
    private String nome;
    private String banner;
    private String tipoHorasComplementares;
    private String dataInicial;
    private String dataFinal;
    private String horarioInicial;
    private String horarioFinal;
    private int limiteDedescrição;
    private String descricao;
    private Long idCurso;
    private Long matriculaProfessor;
}
