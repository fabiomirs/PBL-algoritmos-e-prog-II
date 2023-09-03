package model;

import dao.DAO;
import dao.livro.LivroDAO;

import java.util.List;

public abstract class Pessoa {
    private String nome;
    private Integer numIdentificacao;

    public Pessoa(String nome, Integer numIdentificacao) {
        this.nome = nome;
        this.numIdentificacao = numIdentificacao;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumIdentificacao() {
        return numIdentificacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumIdentificacao(Integer numIdentificacao) {
        this.numIdentificacao = numIdentificacao;
    }

}
