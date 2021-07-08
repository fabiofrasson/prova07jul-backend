package com.fabiofrasson.prova07jul.resource;

import com.fabiofrasson.prova07jul.domain.Empresa;
import com.fabiofrasson.prova07jul.domain.Produto;
import com.fabiofrasson.prova07jul.dto.EmpresaDto;
import com.fabiofrasson.prova07jul.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaResource {

  private final EmpresaService service;

  public EmpresaResource(EmpresaService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Empresa>> listarEmpresas() {
    return ResponseEntity.ok(service.listarTodas());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Empresa> acharPorId(@PathVariable("id") Long id) {
    return ResponseEntity.ok(service.findByIdOrThrowBadRequestException(id));
  }

  @PostMapping
  public ResponseEntity<String> salvar(@RequestBody Empresa empresa) {
    return new ResponseEntity<>(service.salvar(empresa), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    service.Delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<Void> alterar(@RequestBody EmpresaDto empresaDto) {
    service.replace(empresaDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
