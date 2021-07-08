package com.fabiofrasson.prova07jul.resource;

import com.fabiofrasson.prova07jul.domain.Produto;
import com.fabiofrasson.prova07jul.dto.ProdutoDto;
import com.fabiofrasson.prova07jul.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoResource {

  private final ProdutoService service;

  public ProdutoResource(ProdutoService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Produto>> listarProdutos() {
    return ResponseEntity.ok(service.listarTodos());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Produto> acharPorId(@PathVariable("id") Long id) {
    return ResponseEntity.ok(service.findByIdOrThrowBadRequestException(id));
  }

  @GetMapping(path = "/empresa/{idEmpresa}")
  public ResponseEntity<List<Produto>> procurarPorEmpresa(@PathVariable("idEmpresa") Long idEmpresa) {
    List<Produto> produtos = service.procurarPorEmpresa(idEmpresa);
    return ResponseEntity.ok(produtos);
  }

  @PostMapping
  public ResponseEntity<String> salvar(@RequestBody Produto produto) {
    return new ResponseEntity<>(service.salvar(produto), HttpStatus.CREATED);
  }

  @DeleteMapping
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    service.Delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<Void> alterar(@RequestBody ProdutoDto produtoDto) {
    service.replace(produtoDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
