package br.com.fiap.model.dto;

import jakarta.validation.constraints.*;

public record HabilidadeDTO(
        @NotBlank
        String habilidade,

        @NotBlank
        String categoria,

        @NotBlank
        String nivelAtual,

        @NotBlank
        String meta,

        @NotBlank
        String funcionario,

        @NotBlank
        String status
) {}
