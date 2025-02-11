package com.uece.horas_complementares.model.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorEventoDTO {
    private Long id;
    private String nome;
    private String email;
}
