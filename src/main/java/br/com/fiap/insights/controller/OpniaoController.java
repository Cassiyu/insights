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

import br.com.fiap.insights.model.Opniao;
import br.com.fiap.insights.repository.OpniaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("opniao")
@Slf4j
public class OpniaoController {

    @Autowired
    OpniaoRepository repository;

    @GetMapping
    public List<Opniao> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Opniao create(@RequestBody @Valid Opniao opniao) {
        log.info("cadastrando opniao: {}", opniao);
        return repository.save(opniao);
    }

    @GetMapping("{id}")
    public ResponseEntity<Opniao> show(@PathVariable Long id) {
        log.info("buscando opniao com id: {}", id);

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando opniao{}", id);
        verificarSeExisteOpniao(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Opniao update(@PathVariable Long id, @RequestBody Opniao opniao) {
        log.info("atualizando opniao com id {} para {}", id, opniao);

        verificarSeExisteOpniao(id);
        opniao.setId(id);
        return repository.save(opniao);
    }

    private void verificarSeExisteOpniao(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Id da opniao não encontrado"
                ));
    }
}

