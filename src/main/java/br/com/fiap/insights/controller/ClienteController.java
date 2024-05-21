package br.com.fiap.insights.controller;

import java.awt.print.Pageable;
import java.util.List;

import br.com.fiap.insights.dto.cliente.AtualizarCliente;
import br.com.fiap.insights.dto.cliente.CadastrarCliente;
import br.com.fiap.insights.dto.cliente.DetalhesCliente;
import br.com.fiap.insights.dto.cliente.ListagemCliente;
import br.com.fiap.insights.dto.opiniao.CadastrarOpiniao;
import br.com.fiap.insights.dto.opiniao.DetalhesOpiniao;
import br.com.fiap.insights.dto.produto.CadastrarProduto;
import br.com.fiap.insights.dto.produto.DetalhesProduto;
import br.com.fiap.insights.model.Opiniao;
import br.com.fiap.insights.model.Produto;
import br.com.fiap.insights.repository.OpiniaoRepository;
import br.com.fiap.insights.repository.ProdutoRepository;
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


import br.com.fiap.insights.model.Cliente; 
import br.com.fiap.insights.repository.ClienteRepository; 
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cliente") 
@Slf4j
public class ClienteController {


        @Autowired
        ClienteRepository clienteRepository;

        @Autowired
        OpiniaoRepository opiniaoRepository;

        @Autowired
        ProdutoRepository produtoRepository;


      @PutMapping("{idCliente}/opiniao/{idOpiniao}")
      @Transactional
      public ResponseEntity<DetalhesCliente> put(@PathVariable("idCliente") Long idCliente, @PathVariable("idOpiniao") Long idOpiniao){
      var cliente = clienteRepository.getReferenceById(idCliente);
      var opiniao = opiniaoRepository.getReferenceById(idOpiniao);
      opiniao.setCliente(cliente);
      return ResponseEntity.ok().body(new DetalhesCliente(cliente));
      }


        @PostMapping("{id}/produto")
        @Transactional
        public ResponseEntity<DetalhesProduto> post(@PathVariable ("id") Long id,
                                                    @RequestBody @Valid CadastrarProduto dto,
                                                    UriComponentsBuilder uriBuilder) {
            var cliente = clienteRepository.getReferenceById(id);
            var produto = new Produto(dto);
            produto.setCliente(cliente);
            produtoRepository.save(produto);
            var uri = uriBuilder.path("produto/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetalhesProduto(produto));
        }

        @DeleteMapping("{id}")
        @Transactional
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        @PutMapping("{id}")
        @Transactional
        public ResponseEntity<DetalhesCliente> put(@PathVariable("id") Long id,
                                                   @RequestBody @Valid AtualizarCliente dto) {
            var cliente = clienteRepository.getReferenceById(id);
            cliente.atualizarCliente(dto);
            return ResponseEntity.ok(new DetalhesCliente(cliente));
        }

        @GetMapping("{id}")
        public ResponseEntity<DetalhesCliente> get(@PathVariable("id") Long id) {
            var cliente = clienteRepository.getReferenceById(id);
            return ResponseEntity.ok(new DetalhesCliente(cliente));
        }

        @GetMapping
        public ResponseEntity<List<ListagemCliente>> get(Pageable pageable) {
            var listaDto = clienteRepository.findAll().stream().map(ListagemCliente::new).toList();
            return ResponseEntity.ok(listaDto);
        }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCliente> create(@RequestBody @Valid CadastrarCliente dto,
                                                  UriComponentsBuilder builder){
        var cliente = new Cliente(dto);
        clienteRepository.save(cliente);
        var url = builder.path("cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesCliente(cliente));
    }
}