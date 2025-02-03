package com.uece.horas_complementares.model.DTO.user;

import java.util.List;

public record LoginResponseDTO(String token, String nome,String email,Long matricula) {
}
