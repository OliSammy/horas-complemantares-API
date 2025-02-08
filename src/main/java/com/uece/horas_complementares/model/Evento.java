package com.uece.horas_complementares.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.user.Professor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Evento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String banner;//gabriel como bota isso como imagem ?S KSKSKSKSK
    private String tipoHorasComplementares;
    private String dataInicial;
    private String dataFinal;
    private String horarioInicial;
    private String horarioFinal;
    private int limiteDedescrição;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idCurso") // Fk para tabela Curso
    @JsonIgnore
    private Curso idCurso;

    @ManyToOne
    @JoinColumn(name = "matriculaProfessor") // Fk para tabela Professor
    @JsonIgnore
    private Professor matriculaProfessor;

    @OneToMany(mappedBy = "idEvento") // Relacionamento com a tabela Inscrição
    @JsonIgnore
    private List<Inscricao> inscricoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getTipoHorasComplementares() {
        return tipoHorasComplementares;
    }

    public void setTipoHorasComplementares(String tipoHorasComplementares) {
        this.tipoHorasComplementares = tipoHorasComplementares;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public int getLimiteDedescrição() {
        return limiteDedescrição;
    }

    public void setLimiteDedescrição(int limiteDedescrição) {
        this.limiteDedescrição = limiteDedescrição;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Professor getMatriculaProfessor() {
        return matriculaProfessor;
    }
    public void setMatriculaProfessor(Professor matriculaProfessor) {
        this.matriculaProfessor = matriculaProfessor;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    
}