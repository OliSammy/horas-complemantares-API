package com.uece.horas_complementares.model.repository;

import com.uece.horas_complementares.model.Aluno;
import com.uece.horas_complementares.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno>,UserRepositoryQueries{
    Optional<User> findByEmail(String email);


}
