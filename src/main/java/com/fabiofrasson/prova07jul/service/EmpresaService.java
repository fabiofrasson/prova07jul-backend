package com.fabiofrasson.prova07jul.service;

import com.fabiofrasson.prova07jul.domain.Empresa;
import com.fabiofrasson.prova07jul.domain.Produto;
import com.fabiofrasson.prova07jul.dto.EmpresaDto;
import com.fabiofrasson.prova07jul.exception.BadRequestException;
import com.fabiofrasson.prova07jul.modelMapper.ModelMapperConfig;
import com.fabiofrasson.prova07jul.repository.EmpresaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmpresaService {

  private final EmpresaRepository repository;
  private final ModelMapperConfig modelMapper;

  public EmpresaService(EmpresaRepository repository, ModelMapperConfig modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  public List<Empresa> listarTodas() {
    return repository.findAll();
  }

  public Empresa findByIdOrThrowBadRequestException(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new BadRequestException("Empresa não encontrada, por favor tente novamente."));
  }

  @Transactional
  public String salvar(Empresa empresa) {
    Empresa empresa1 = repository.findByNomeIgnoreCase(empresa.getNome());
    if (empresa1 != null) {
      throw new BadRequestException("Empresa " + empresa.getNome() + " já existe.");
    } else {
      repository.save(empresa);
      return "Empresa criada com sucesso!";
    }
  }

  public void Delete(Long id) {
    repository.delete(findByIdOrThrowBadRequestException(id));
  }

  public void replace(EmpresaDto empresaDto) {
    Empresa registroEmpresa = findByIdOrThrowBadRequestException(empresaDto.getId());
    modelMapper.modelMapper().map(empresaDto, registroEmpresa);
    repository.save(registroEmpresa);
  }
}
