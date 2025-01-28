package com.uece.horas_complementares.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import java.util.List;

@Entity
public class HoraComplementar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;//como bota imagemmmm aghhh
    private String status;
    private String comentario;

    
    @OneToMany(mappedBy = "subCategoria") // Relacionamento com a tabela SubCategoria
    @JoinColumn(name = "subCategoria")
    private List<SubCategoria> subCategorias;

    @ManyToOne
    @JoinColumn(name = "matriculaAluno") // Fk para tabela Aluno
    private Aluno matriculaAluno;
}
