package com.kaos.escola.dto;

import jakarta.persistence.Column;
import org.antlr.v4.runtime.misc.NotNull;

public record AdminDTO(
        Long id,
        @NotNull
        @Column(nullable = false) String nome,
        @NotNull
        @Column(nullable = false) String senha,
        @NotNull
        @Column(nullable = false) String matricula
) {
}
