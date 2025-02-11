package com.uece.horas_complementares.model.DTO.evento;

import com.uece.horas_complementares.model.user.Professor;

public record EventoDTO(String bannerEndereco, String descricao, String endereco, String nome, Professor organizador, String tipoHora, String dataInicio, String dataFim, String horaInicio, String horaFim) {
}
