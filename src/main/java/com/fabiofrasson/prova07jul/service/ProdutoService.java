package com.fabiofrasson.prova07jul.service;

import com.fabiofrasson.prova07jul.domain.Empresa;
import com.fabiofrasson.prova07jul.domain.Produto;
import com.fabiofrasson.prova07jul.dto.ProdutoDto;
import com.fabiofrasson.prova07jul.exception.BadRequestException;
import com.fabiofrasson.prova07jul.modelMapper.ModelMapperConfig;
import com.fabiofrasson.prova07jul.repository.EmpresaRepository;
import com.fabiofrasson.prova07jul.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

  private final ProdutoRepository repository;
  private final ModelMapperConfig modelMapper;
  private final EmpresaService service;

  public ProdutoService(ProdutoRepository repository, ModelMapperConfig modelMapper, EmpresaRepository empresaRepository, EmpresaService service) {
    this.repository = repository;
    this.modelMapper = modelMapper;
    this.service = service;
  }

  public List<Produto> listarTodos() {
    return repository.findAll();
  }

  public Produto findByIdOrThrowBadRequestException(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new BadRequestException("Produto não encontrado, por favor tente novamente"));
  }

  public List<Produto> procurarPorEmpresa(Long idEmpresa) {
    Empresa empresa = service.findByIdOrThrowBadRequestException(idEmpresa);
    return repository.findByEmpresa(empresa);
  }

  @Transactional
  public String salvar(Produto produto) {
    Produto produto1 = repository.findByNomeIgnoreCase(produto.getNome());
    if (produto1 != null) {
      throw new BadRequestException("Produto " + produto.getNome() + " já existe.");
    } else {
      repository.save(produto);
      return "Produto adicionado ao catálogo!";
    }
  }

  public void Delete(Long id) {
    repository.delete(findByIdOrThrowBadRequestException(id));
  }

  public void replace(ProdutoDto produtoDto) {
    Produto registroProduto = findByIdOrThrowBadRequestException(produtoDto.getId());
    modelMapper.modelMapper().map(produtoDto, registroProduto);
    repository.save(registroProduto);
  }
}
