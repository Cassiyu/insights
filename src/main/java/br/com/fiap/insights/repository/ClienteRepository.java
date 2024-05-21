package br.com.fiap.insights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.insights.model.Cliente;

import java.awt.print.Pageable;
import java.util.Arrays;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
