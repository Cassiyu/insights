package br.com.fiap.insights.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.insights.model.Cliente; 
import br.com.fiap.insights.repository.ClienteRepository; 
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("cliente") 
@Slf4j
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @GetMapping
    public List<Cliente> index() { 
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente create(@RequestBody @Valid Cliente cliente) { 
        log.info("Cadastrando cliente: {}", cliente); 
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> show(@PathVariable Long id) { 
        log.info("Buscando cliente com id: {}", id);

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando cliente {}", id);
        verificarSeExisteCliente(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        log.info("Atualizando cliente com id {} para {}", id, cliente);

        verificarSeExisteCliente(id);
        cliente.setId(id);
        return repository.save(cliente);
    }

    private void verificarSeExisteCliente(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Id do cliente não encontrado"
                ));
    }
}
