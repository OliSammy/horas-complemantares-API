package com.uece.horas_complementares.service.users;

import com.uece.horas_complementares.model.user.Aluno;
import java.util.List;

import com.uece.horas_complementares.model.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Aluno buscar(Long matricula) {
        return alunoRepository.findByMatricula(matricula);
    }
    // public List<Evento> listarEventos(Long id) {
    //     return alunoRepository.findById(id).get().getEventos();
    // }//falta implementar
    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    public Aluno atualizar(Long matricula, Aluno aluno) {
        Aluno alunoAtualizado = alunoRepository.findByMatricula(matricula);
        alunoAtualizado.setNome(aluno.getNome());
        alunoAtualizado.setEmail(aluno.getEmail());
        alunoAtualizado.setSenha(aluno.getSenha());
        alunoAtualizado.setMatricula(aluno.getMatricula());
        return alunoRepository.save(alunoAtualizado);
    }
    public void deletar(Long matricula) {
        alunoRepository.deleteByMatricula(matricula);
    }

}
