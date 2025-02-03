package com.uece.horas_complementares.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.Curso;
import com.uece.horas_complementares.model.HoraComplementar;
import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.Presenca;
import com.uece.horas_complementares.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ALUNO")
@ToString(onlyExplicitlyIncluded = true)
public class Aluno extends User {
    @ManyToOne
    @JoinColumn(name = "idCurso")
    @JsonIgnore
    private Curso curso;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<Inscricao> inscricoes;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<Presenca> presencas;

    @OneToOne(mappedBy = "aluno")
    @JsonIgnore
    private HoraComplementar horaComplementar;

    // Construtor explícito para chamar super()
    public Aluno(
            Long matricula,       // Parâmetros da classe User
            String nome,
            String email,
            boolean emailValidado,
            String senha,
            String token,
            TipoUsuario tipoUsuario,
            Curso curso,          // Parâmetros específicos de Aluno
            List<Inscricao> inscricoes,
            List<Presenca> presencas,
            HoraComplementar horaComplementar
    ) {
        // Chama o construtor da classe pai (User)
        super(matricula, nome, email, emailValidado, senha, token, tipoUsuario);

        // Inicializa os atributos específicos de Aluno
        this.curso = curso;
        this.inscricoes = inscricoes;
        this.presencas = presencas;
        this.horaComplementar = horaComplementar;
    }
}