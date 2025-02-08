package com.uece.horas_complementares.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@DiscriminatorValue("PROFESSOR")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends User {

    private boolean coordenador;

    @ManyToOne
    @JoinColumn(name = "idCurso")
    @JsonIgnore
    private Curso curso;


    public Professor(
            Long matricula,       // Parâmetros da classe User
            String nome,
            String email,
            boolean emailValidado,
            String senha,
            String token,
            User.TipoUsuario tipoUsuario,
            Curso curso,          // Parâmetros específicos de Aluno
            boolean coordenador
             )
              {
        super(matricula, nome, email, emailValidado, senha, token, tipoUsuario);

        this.curso = curso;
        this.coordenador = coordenador;
    }
    private Set<Aluno> alunos; // Add this field

    // other fields and methods

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
    public Aluno getAluno() {
        return null;
    }
}



