package model;

import dao.DAO;
import dao.livro.LivroDAO;

import java.util.List;
import java.util.Objects;

public abstract class Pessoa {
    private String nome;
    private int numIdentificacao;

    public Pessoa(String nome) {
        this.nome = nome;
        this.numIdentificacao = -1;
    }

    public Pessoa(String nome, int proximoId) {
        this.nome = nome;
        this.numIdentificacao = proximoId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return numIdentificacao == pessoa.numIdentificacao && Objects.equals(nome, pessoa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numIdentificacao);
    }
}
