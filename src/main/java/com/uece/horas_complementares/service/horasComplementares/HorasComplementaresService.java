package com.uece.horas_complementares.service.horasComplementares;


import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.HoraComplementar;
import com.uece.horas_complementares.model.repository.EventoRepository;
import com.uece.horas_complementares.model.repository.HorasComplementaresRepository;
import com.uece.horas_complementares.model.spec.EventoByIdSpec;
import com.uece.horas_complementares.model.spec.HoraComplementarByAlunoIdSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HorasComplementaresService {



    @Autowired
    private HorasComplementaresRepository horasComplementaresRepository;

    @Autowired
    private EventoRepository eventoRepository;
    public HoraComplementar listar(Long alunoId) {
        Specification<HoraComplementar> spec = new HoraComplementarByAlunoIdSpec(alunoId);
        Optional<HoraComplementar> newHoraComplementar = horasComplementaresRepository.findOne(spec);
        if (newHoraComplementar.isEmpty()){
            throw new RuntimeException("Aluno não encontrado");
        }
        return newHoraComplementar.get();
    }
    public void adicionarHorasComplementares(Long alunoId, Long eventoId) {
        Specification<HoraComplementar> spec = new HoraComplementarByAlunoIdSpec(alunoId);
        Optional<HoraComplementar> newHoraComplementar = horasComplementaresRepository.findOne(spec);
        if (newHoraComplementar.isEmpty()){
            throw new RuntimeException("Aluno não encontrado");
        }

        HoraComplementar hrComp = newHoraComplementar.get();

        Specification<Evento> specEvento = new EventoByIdSpec(eventoId);
        Optional<Evento> newEvento = eventoRepository.findOne(specEvento);

        if (newEvento.isEmpty()){
            throw new RuntimeException("Evento não encontrado");
        }

        Evento evento = newEvento.get();

        if (hrComp.getHorasComplementares() >= 200){
            throw new RuntimeException("Aluno já atingiu o limite de horas complementares");
        }else if (hrComp.getHorasComplementares() + evento.getQuantidadeHorasComplementares() > 200){
            hrComp.setHorasComplementares(200);

        }else{
            System.out.println(hrComp.getHorasComplementares() + evento.getQuantidadeHorasComplementares());
            hrComp.setHorasComplementares(hrComp.getHorasComplementares() + evento.getQuantidadeHorasComplementares());
        }

        horasComplementaresRepository.save(hrComp);

    }
}
