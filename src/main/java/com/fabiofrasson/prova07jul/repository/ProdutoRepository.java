package com.fabiofrasson.prova07jul.repository;

import com.fabiofrasson.prova07jul.domain.Empresa;
import com.fabiofrasson.prova07jul.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  Produto findByNomeIgnoreCase(String nome);
  List<Produto> findByEmpresa(Empresa empresa);
}
