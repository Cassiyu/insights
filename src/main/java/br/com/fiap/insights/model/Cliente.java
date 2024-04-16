package br.com.fiap.insights.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String cidade;

    @NotNull
    private Integer idade;

    // 'M' (masculino) 'F' (feminino) 'Indefinido'
    @Size(min = 1, max = 1)
    private String sexo;

}