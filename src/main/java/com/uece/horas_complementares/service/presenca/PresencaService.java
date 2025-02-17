package com.uece.horas_complementares.service.presenca;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.Presenca;
import com.uece.horas_complementares.model.repository.InscricaoRepository;
import com.uece.horas_complementares.model.repository.PresencaRepository;

import lombok.Data;

import java.util.Optional;

@Data
@Service
public class PresencaService {

    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public void confirmarPresenca(Long idEvento, Long matriculaAluno) {
        // Encontrar a inscrição do aluno no evento
        List<Inscricao> inscricoes = inscricaoRepository.findByIdEvento_IdAndAluno_Matricula(idEvento, matriculaAluno);
        if (inscricoes.isEmpty()) {
            throw new RuntimeException("Inscrição não encontrada para o aluno no evento.");
        }
            Presenca presenca = presencaRepository.findByIdInscricao_IdEvento_IdAndAluno_Matricula(idEvento, matriculaAluno);
            presenca.setPresente(true);
            presencaRepository.save(presenca);
        
        }
    }

