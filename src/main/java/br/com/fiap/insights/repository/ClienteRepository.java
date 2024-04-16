package br.com.fiap.insights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.insights.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
