package com.example.pbl.model;

import java.util.Objects;

/**
 * A classe Usuario representa de forma geral um indivíduo usuario no sistema de biblioteca.
 * É uma subclasse da classe Pessoa.
 */
public class Usuario extends Pessoa {
    /**
     * Enderço correspondente ao Usuário.
     */
    private String endereco;
    /**
     * Telefone correspondente ao usuário.
     */
    private Integer telefone;
    /**
     * Status que se encontra o usuário. (Bloqueado ou Liberado).
     */
    private String statusConta;
    /**
     * Limite de renovações do usuário.
     */
    private Integer limRenovacao;

    /**
     * Cria uma instância para Usuario com os parâmetros listados abaixo.
     * @param nome Representa o nome do Usuario.
     * @param endereco Representa o endereço do Usuario.
     * @param telefone Representa o telefone do Usuario.
     * @param id Representa o id de identficação do Usuario.
     * @param statusConta Representa o status da conta do Usuario.
     */
    public Usuario(String nome ,String endereco, Integer telefone, int id, String statusConta) {
        super(nome, id);
        this.endereco = endereco;
        this.telefone = telefone;
        this.statusConta = statusConta;
        this.limRenovacao = 2;
    }

    /**
     * Cria uma instância para Usuario com os parâmetros listados abaixo.
     * @param nome Representa o nome do Usuario.
     * @param endereco Representa o endereço do Usuario.
     * @param telefone Representa o telefone do Usuario.
     */
    public Usuario(String nome ,String endereco, Integer telefone) {
        super(nome);
        this.endereco = endereco;
        this.telefone = telefone;
        this.statusConta = "Liberado";
        this.limRenovacao = 2;
    }

    /**
     * Obtém o endereço do usuário.
     * @return O endereço.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Determina o endereço do usuário.
     * @param endereco O endereço determinado.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém o telefone do usuário.
     * @return O telefone.
     */
    public Integer getTelefone() {
        return telefone;
    }

    /**
     * Determina o telefone do usuário.
     * @param telefone O telefone determinado.
     */
    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o status da conta do usuário.
     * @return O status da conta.
     */
    public String getStatusConta() {
        return statusConta;
    }

    /**
     * Determina o status da conta do usuário.
     * @param statusConta O status determinado.
     */
    public void setStatusConta(String statusConta) {
        this.statusConta = statusConta;
    }

    /**
     * Obtém a quantidade de renovações possíveis do usuário.
     * @return A quantidade de renovações possíveis.
     */
    public Integer getLimRenovacao() {
        return limRenovacao;
    }

    /**
     * Determina o limite de renovações possíveis para um usuário.
     * @param limRenovacao O limite determinado.
     */
    public void setlimRenovacao(Integer limRenovacao) {
        this.limRenovacao= limRenovacao;
    }

    /**
     *
     * @param o Objeto a ser comparado.
     * @return True ou False a depender do resultado da comparação.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(endereco, usuario.endereco) && Objects.equals(telefone, usuario.telefone) && Objects.equals(statusConta, usuario.statusConta) && Objects.equals(limRenovacao, usuario.limRenovacao);
    }
}
