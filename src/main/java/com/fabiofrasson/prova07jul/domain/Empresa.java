package com.fabiofrasson.prova07jul.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Empresa implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @OneToMany(mappedBy = "empresa")
  private List<Produto> produtos = new ArrayList<>();

  public Empresa() {}

  public Empresa(String nome) {
    this.nome = nome;
  }

  public Empresa(Long id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public Empresa(Long id, String nome, List<Produto> produtos) {
    this.id = id;
    this.nome = nome;
    this.produtos = produtos;
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

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }
}
