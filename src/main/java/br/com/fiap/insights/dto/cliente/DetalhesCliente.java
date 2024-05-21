package br.com.fiap.insights.dto.cliente;

import br.com.fiap.insights.model.Cliente;
import br.com.fiap.insights.model.Produto;

public record DetalhesCliente(Long id,String cidade, Integer idade, String sexo) {
    public DetalhesCliente(Cliente cliente){

        this( cliente.getId(),cliente.getCidade(), cliente.getIdade(), cliente.getSexo());
    }
}
