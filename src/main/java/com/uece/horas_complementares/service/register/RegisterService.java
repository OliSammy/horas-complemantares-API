package com.uece.horas_complementares.service.register;

import com.uece.horas_complementares.model.Curso;
import com.uece.horas_complementares.model.DTO.user.AuthenticationDTO;
import com.uece.horas_complementares.model.DTO.user.LoginResponseDTO;
import com.uece.horas_complementares.model.HoraComplementar;
import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.Presenca;
import com.uece.horas_complementares.model.exception.auth.InvalidLoginException;
import com.uece.horas_complementares.model.exception.user.UserNotFoundException;
import com.uece.horas_complementares.model.repository.UserRepository;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.user.RegisterForm;
import com.uece.horas_complementares.model.user.User;
import com.uece.horas_complementares.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User create(RegisterForm data) throws Exception {
        if(this.repository.findByEmail(data.email()) != null){
            throw new Exception("O email já está sendo usado, por favor escolha outro.");
        }

        Optional<UserDetails> matricula = Optional.ofNullable(this.repository.findByMatricula(data.matricula()));
        if(matricula.isPresent()){
            throw new Exception("A matrícula já está cadastrado");
        }
        User newUser = null;

        if (data.role().equals(User.TipoUsuario.ALUNO)){
            newUser = new Aluno();
            newUser.setMatricula(data.matricula());
            newUser.setNome(data.name());
            newUser.setTipoUsuario(data.role());
            newUser.setEmail(data.email());
            ((Aluno) newUser).setCurso(data.curso());

        } else {
            newUser = new Professor();
            newUser.setMatricula(data.matricula());
            newUser.setNome(data.name());
            newUser.setTipoUsuario(data.role());
            newUser.setEmail(data.email());
            ((Professor) newUser).setCoordenador(data.coordenador());
            ((Professor) newUser).setCurso(data.curso());

        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        newUser.setSenha(encryptedPassword);
        this.repository.save(newUser);
        return newUser;
    }

}
