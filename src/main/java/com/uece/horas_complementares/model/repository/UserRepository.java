package com.uece.horas_complementares.model.repository;
import com.uece.horas_complementares.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>,UserRepositoryQueries{
    UserDetails findByEmail(String email);

    UserDetails findByMatricula(Long matricula);


}
