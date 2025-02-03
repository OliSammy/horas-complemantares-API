package com.uece.horas_complementares.service.auth;

import com.uece.horas_complementares.model.Aluno;
import com.uece.horas_complementares.model.DTO.user.AuthenticationDTO;
import com.uece.horas_complementares.model.DTO.user.LoginResponseDTO;
import com.uece.horas_complementares.model.exception.auth.InvalidLoginException;
import com.uece.horas_complementares.model.exception.user.UserNotFoundException;
import com.uece.horas_complementares.model.repository.UserRepository;
import com.uece.horas_complementares.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        UserDetails newUser = this.repository.findByEmail(data.email()).get();

        if(newUser == null){
            throw new InvalidLoginException("Email ou senha inválidos");
        }

        Aluno usuario = ((Aluno) newUser);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(data.password(), usuario.getSenha())) {
            throw  new InvalidLoginException("Email ou senha inválidos!");
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Aluno) auth.getPrincipal());

        UserDetails userDetails = repository.findByEmail(data.email()).get();
        String nome = ((Aluno) userDetails).getNome();
        Long matricula = (((Aluno) userDetails).getMatricula());
        String email = (((Aluno) userDetails).getEmail());


        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        LoginResponseDTO response = new LoginResponseDTO(token,nome,email,matricula);
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
