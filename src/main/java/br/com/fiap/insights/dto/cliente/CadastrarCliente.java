package br.com.fiap.insights.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastrarCliente(

        @NotBlank
        @Size(max=80)
        String cidade,

        @NotNull
        Integer idade,
        @Size(min = 1, max = 1)
        String sexo) {
}
