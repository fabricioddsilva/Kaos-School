package com.kaos.escola.dto;

import jakarta.persistence.Column;
import org.antlr.v4.runtime.misc.NotNull;

public record AlunosDTO(
        Long id,
        @NotNull
        @Column(nullable = false) String nome,
        @NotNull
        @Column(nullable = false) int idade,
        @NotNull
        @Column(nullable = false) String turma,
        @NotNull
        @Column(nullable = false) String turno
) {
}
