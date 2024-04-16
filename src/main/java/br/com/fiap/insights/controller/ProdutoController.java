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

import br.com.fiap.insights.model.Produto;
import br.com.fiap.insights.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("produto")
@Slf4j
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public List<Produto> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto create(@RequestBody @Valid Produto produto) {
        log.info("cadastrando produto: {}", produto);
        return repository.save(produto);
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> show( @PathVariable Long id){
        log.info("buscando produto com id {}", id);

        return repository
                    .findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build()); 
    }   

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando produto {}", id);
        verificarSeExisteProduto(id);
        repository.deleteById(id);
    }


    @PutMapping("{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto){
        log.info("atualizando produto com id {} para {}", id, produto);

        verificarSeExisteProduto(id);
        produto.setId(id);
        return repository.save(produto);
    }

    private void verificarSeExisteProduto(Long id){
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                                    NOT_FOUND,
                                    "id do produto não encontrado"
                                    ));
    }
}