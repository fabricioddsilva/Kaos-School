package com.kaos.escola.models;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table(name="tb_alunos")
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private int idade;

    @NotNull
    @Column(nullable = false)
    private String turma;

    @NotNull
    @Column(nullable = false)
    private String turno;



}
