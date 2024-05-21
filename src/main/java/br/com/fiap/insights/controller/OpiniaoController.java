package br.com.fiap.insights.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.awt.print.Pageable;
import java.util.List;

import br.com.fiap.insights.dto.cliente.AtualizarCliente;
import br.com.fiap.insights.dto.cliente.CadastrarCliente;
import br.com.fiap.insights.dto.cliente.DetalhesCliente;
import br.com.fiap.insights.dto.cliente.ListagemCliente;
import br.com.fiap.insights.dto.opiniao.AtualizarOpiniao;
import br.com.fiap.insights.dto.opiniao.CadastrarOpiniao;
import br.com.fiap.insights.dto.opiniao.DetalhesOpiniao;
import br.com.fiap.insights.dto.opiniao.ListagemOpiniao;
import br.com.fiap.insights.model.Cliente;
import jakarta.transaction.Transactional;
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

import br.com.fiap.insights.model.Opiniao;
import br.com.fiap.insights.repository.OpiniaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("opiniao")
@Slf4j
public class OpiniaoController {

    @Autowired
    OpiniaoRepository opiniaoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesOpiniao> post(@RequestBody @Valid CadastrarOpiniao dto,
                                                UriComponentsBuilder uriBuilder) {
        var opiniao = new Opiniao(dto);
        opiniaoRepository.save(opiniao);
        var uri = uriBuilder.path("/opiniao/{id}").buildAndExpand(opiniao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesOpiniao(opiniao));

    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        opiniaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesOpiniao> put(@PathVariable("id") Long id,
                                               @RequestBody AtualizarOpiniao dto) {
        var opiniao = opiniaoRepository.getReferenceById(id);
        opiniao.atualizarOpiniao(dto);
        return ResponseEntity.ok(new DetalhesOpiniao(opiniao));

    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesOpiniao> get(@PathVariable("id") Long id) {
        var opiniao = opiniaoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesOpiniao(opiniao));

    }

    @GetMapping
    public ResponseEntity<List<ListagemOpiniao>> get(Pageable pageable) {
        var ListaDto = opiniaoRepository.findAll().stream().map(ListagemOpiniao::new).toList();
        return ResponseEntity.ok(ListaDto);
    }

}

