package com.uece.horas_complementares.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.exception.token.InvalidTokenException;
import com.uece.horas_complementares.model.repository.UserRepository;
import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;


    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("Complementary_Hours").withSubject(user.getEmail()).withExpiresAt(genExpirationDate()).sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Complementary_Hours")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (InvalidTokenException exception){

            throw new InvalidTokenException("Token JWT invalido ou expirado");
        }catch (TokenExpiredException ex){
            return null;
        }
    }
    public String generateTokenEvento(Long idEvento, Long matriculaAluno) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("Complementary_Hours")
                .withClaim("idEvento", idEvento)
                .withClaim("matriculaAluno", matriculaAluno)
                .withExpiresAt(genExpirationDate())
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating event token", exception);
        }
    }
    public String validateEventToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Complementary_Hours")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (InvalidTokenException exception){

            throw new InvalidTokenException("Token JWT invalido ou expirado");
        }catch (TokenExpiredException ex){
            return null;
        }
    }

    public User getUserFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("Complementary_Hours").build().verify(token);

            String userEmail = decodedJWT.getSubject();

            // Busca o usuário sem fazer casting direto
            User user = (User) userRepository.findByEmail(userEmail);

            // Verifica o tipo do usuário
            if (user instanceof Aluno) {
                return (Aluno) user; // Cast seguro para Aluno
            } else if (user instanceof Professor) {
                return (Professor) user; // Cast seguro para Professor
            } else {
                throw new Exception("Tipo de usuário não reconhecido");
            }

        } catch (Exception exception) {
            throw new InvalidTokenException("Token JWT inválido");
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }
}
