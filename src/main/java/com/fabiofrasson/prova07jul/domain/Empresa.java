package com.fabiofrasson.prova07jul.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Empresa implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @OneToMany private Set<Produto> produtos = new HashSet<>();

  public Empresa() {}

  public Empresa(String nome) {
    this.nome = nome;
  }

  public Empresa(Long id, String nome) {
    this.id = id;
    this.nome = nome;
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

  public Set<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(Set<Produto> produtos) {
    this.produtos = produtos;
  }
}
