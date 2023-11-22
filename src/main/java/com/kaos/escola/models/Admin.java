package com.kaos.escola.models;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table (name = "tb_admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(length = 30,nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private String senha;

    @NotNull
    @Column(length = 10, nullable = false)
    private String matricula;



}
