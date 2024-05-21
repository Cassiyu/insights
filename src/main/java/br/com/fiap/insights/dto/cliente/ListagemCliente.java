package br.com.fiap.insights.dto.cliente;

import br.com.fiap.insights.model.Cliente;

public record ListagemCliente(Long id,String cidade, Integer idade, String sexo) {
    public ListagemCliente(Cliente cliente) {
        this(cliente.getId(),cliente.getCidade(), cliente.getIdade(), cliente.getSexo());
    }
}
