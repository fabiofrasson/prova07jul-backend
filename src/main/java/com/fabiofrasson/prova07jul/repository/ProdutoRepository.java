package com.fabiofrasson.prova07jul.repository;

import com.fabiofrasson.prova07jul.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  Produto findByNomeIgnoreCase(String nome);
}
