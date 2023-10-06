package model;

import java.util.Objects;

/**
 * A classe Pessoa representa de forma geral um indivíduo com nome e número de identificação.
 */
public class Pessoa {
    /**
     * Nome correspondente a pessoa.
     */
    private String nome;
    /**
     * Número de identificação correspondente a pessoa.
     */
    private int numIdentificacao;

    /**
     * Cria uma instância para Pessoa com o nome e um número de identificação padrão de -1.
     * @param nome Representa o nome da pessoa.
     */
    public Pessoa(String nome) {
        this.nome = nome;
        this.numIdentificacao = -1;
    }

    /**
     * Cria uma instância para Pessoa com o nome e um número de identificação.
     * @param nome Representa o nome da pessoa.
     * @param proximoId Representa a identificação da pessoa por meio de um número.
     */
    public Pessoa(String nome, int proximoId) {
        this.nome = nome;
        this.numIdentificacao = proximoId;
    }

    /**
     * Obtém o nome da pessoa.
     * @return o nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém O número de identificação da pessoa.
     * @return O número de identificação.
     */
    public Integer getNumIdentificacao() {
        return numIdentificacao;
    }

    /**
     * Determina o nome da pessoa.
     * @param nome O nome definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Determina o número de identificação da pessoa.
     * @param numIdentificacao O número determinado.
     */
    public void setNumIdentificacao(Integer numIdentificacao) {
        this.numIdentificacao = numIdentificacao;
    }

    /**
     * Verifica se esta Pessoa é igual a outro objeto com base no nome e número de identificação.
     * @param o O objeto pra comparar.
     * @return True ou False, depende do resultado da comparação.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return numIdentificacao == pessoa.numIdentificacao && Objects.equals(nome, pessoa.nome);
    }
}
