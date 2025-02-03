package com.uece.horas_complementares.service.auth;

import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.DTO.user.AuthenticationDTO;
import com.uece.horas_complementares.model.DTO.user.LoginResponseDTO;
import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.user.User;
import com.uece.horas_complementares.model.exception.auth.InvalidLoginException;
import com.uece.horas_complementares.model.exception.user.UserNotFoundException;
import com.uece.horas_complementares.model.repository.UserRepository;
import com.uece.horas_complementares.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public LoginResponseDTO login(AuthenticationDTO data){
        UserDetails newUser = this.repository.findByEmail(data.email());
        if(newUser == null){
            throw new InvalidLoginException("Email ou senha inválidos");
        }

        User usuario = null;
        if (newUser instanceof Aluno){
            usuario = ((Aluno) newUser);

        }else{
            usuario = ((Professor) newUser);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(data.password(), usuario.getSenha())) {
            throw  new InvalidLoginException("Email ou senha inválidos!");
        }
//
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
//        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken(usuario);

        UserDetails userDetails = repository.findByEmail(data.email());
        String nome;
        Long matricula;
        String email;
        if (userDetails instanceof Aluno){
            nome =     ((Aluno) userDetails).getNome();
            matricula = (((Aluno) userDetails).getMatricula());
            email = (((Aluno) userDetails).getEmail());
        }else{
            nome =     ((Professor) userDetails).getNome();
            matricula = (((Professor) userDetails).getMatricula());
            email = (((Professor) userDetails).getEmail());
        }

        User.TipoUsuario role = usuario.getTipoUsuario();

        LoginResponseDTO response = new LoginResponseDTO(token,nome,email,matricula,role);
//        LoginResponseDTO response = null;

        return response;
    }


    public Aluno get(String token){
        Aluno user = this.tokenService.getUserFromToken(token);
        if (user == null){
            throw new UserNotFoundException("Não foi possível recuperar os dados do usuário");
        }
        return  user;
    }

}
