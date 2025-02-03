package com.uece.horas_complementares.model.DTO.user;

import com.uece.horas_complementares.model.user.User;

public record LoginResponseDTO(String token, String nome, String email, Long matricula, User.TipoUsuario role) {
}
