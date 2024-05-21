package br.com.fiap.insights.controller;

import java.awt.print.Pageable;
import java.util.List;

import br.com.fiap.insights.dto.produto.AtualizarProduto;
import br.com.fiap.insights.dto.produto.CadastrarProduto;
import br.com.fiap.insights.dto.produto.DetalhesProduto;
import br.com.fiap.insights.dto.produto.ListagemProduto;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.insights.model.Produto;
import br.com.fiap.insights.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produto")
@Slf4j
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesProduto> post(@RequestBody @Valid CadastrarProduto dto,
                                                UriComponentsBuilder uriBuilder) {
        var produto = new Produto (dto);
        produtoRepository.save(produto);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesProduto(produto));

    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }


    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesProduto> put(@PathVariable("id") Long id,
                                               @RequestBody AtualizarProduto dto) {
        var produto = produtoRepository.getReferenceById(id);
        produto.atualizarProduto(dto);
        return ResponseEntity.ok(new DetalhesProduto(produto));

    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesProduto> get(@PathVariable("id") Long id) {
        var produto = produtoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesProduto(produto));

    }

    @GetMapping
    public ResponseEntity<List<ListagemProduto>> get(Pageable pageable) {
        var ListaDto = produtoRepository.findAll().stream().map(ListagemProduto::new).toList();
        return ResponseEntity.ok(ListaDto);
    }
}