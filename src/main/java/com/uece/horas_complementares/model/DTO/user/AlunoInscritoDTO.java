package com.uece.horas_complementares.model.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoInscritoDTO {
    private Long matricula;
    private String nome;
    private String email;
}
