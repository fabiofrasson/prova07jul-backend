package com.fabiofrasson.prova07jul.repository;

import com.fabiofrasson.prova07jul.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

  Empresa findByNomeIgnoreCase(String nome);
}
