package com.uece.horas_complementares.model.user;

import com.uece.horas_complementares.model.Curso;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("COORDENADOR")
@Data
@NoArgsConstructor
public class Coordenador extends Professor {

    public Coordenador(
            Long matricula,
            String nome,
            String email,
            boolean emailValidado,
            String senha,
            String token,
            Curso curso,
            boolean coordenador
    ) {
        super(matricula, nome, email, emailValidado, senha, token, User.TipoUsuario.COORDENADOR, curso, coordenador);
    }


}