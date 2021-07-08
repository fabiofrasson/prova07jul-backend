package com.fabiofrasson.prova07jul.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Produto implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private BigDecimal preco;

  @ManyToOne private Empresa empresa;

  public Produto() {}

  public Produto(String nome, BigDecimal preco, Empresa empresa) {
    this.nome = nome;
    this.preco = preco;
    this.empresa = empresa;
  }

  public Produto(Long id, String nome, BigDecimal preco, Empresa empresa) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
    this.empresa = empresa;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }
}
