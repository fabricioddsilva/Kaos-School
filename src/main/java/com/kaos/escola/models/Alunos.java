package com.kaos.escola.models;

import jakarta.persistence.*;

@Entity
@Table(name="tb_alunos")
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private int idade;

    private String turma;

    private String turno;

    public Alunos(){

    }

    public Alunos(long id, String nome, int idade, String turma, String turno) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.turma = turma;
        this.turno = turno;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
