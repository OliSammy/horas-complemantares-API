// package com.uece.horas_complementares.model.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import com.uece.horas_complementares.model.Inscricao;
// import com.uece.horas_complementares.model.Presenca;

// import jakarta.transaction.Transactional;

// @Repository
// public interface PresencaRepository extends JpaRepository<Presenca, Long> {
//     @Modifying
//     @Transactional
//     @Query("UPDATE Presenca p SET p.presente = true WHERE p.evento.id = :eventoId AND p.usuario.id = :usuarioId")
//     void confirmarPresenca(@Param("eventoId") Long eventoId, @Param("usuarioId") Long usuarioId);
// }
