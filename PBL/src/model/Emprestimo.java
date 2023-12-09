package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A classe Emprestimo representa um empréstimo de um livro para um usuário no sistema de biblioteca.
 */
public class Emprestimo implements Serializable {
    /**
     * Objeto do livro.
     */
    private Livro livro;
    /**
     * Objeto do usuário.
     */
    private Usuario usuario;
    /**
     * Data correspondente ao dia que o empréstimo foi feito.
     */
    private LocalDate dataEmprestimo;
    /**
     * Data da devolução do empréstimo.
     */
    private LocalDate dataDevolucao;
    /**
     * Número de identificação para o empréstimo.
     */
    private Integer id;
    /**
     * Status atual que se encontra o empréstimo.(Fechado ou Em aberto).
     */
    private String status;

    /**
     * Cria um novo registro de empréstimo com o livro e usuário especificados.
     * A data de empréstimo é determinada como a data atual e a data de devolução é determinada como 7 dias após a data de empréstimo.
     * @param livro O livro que está sendo emprestado.
     * @param usuario O usuário que está fazendo o empréstimo.
     */
    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = this.dataEmprestimo.plusDays(7);
        this.id = usuario.getNumIdentificacao();
        this.status = "Fechado";
    }

    /**
     * Cria um registro de empréstimo com o livro, usuário e ID especificados.
     * A data de empréstimo é determinada como a data atual e a data de devolução é determinada como 7 dias após a data de empréstimo.
     * @param livro O livro que está sendo emprestado.
     * @param usuario O usuário que está fazendo o empréstimo.
     * @param id O ID único do empréstimo.
     */
    public Emprestimo(Livro livro, Usuario usuario, Integer id) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = this.dataEmprestimo.plusDays(7);
        this.id = id;
        this.status = "Fechado";
    }

    /**
     * Obtém o status do empréstimo.
     * @return O status do empréstimo.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Determina o status do empréstimo.
     * @param status O status do empréstimo a ser determinado.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Determina a data de devolução do empréstimo.
     * @param dataDevolucao A data de devolução a ser determinada.
     */
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * Obtém a data de empréstimo.
     * @return A data de empréstimo.
     */
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * Obtém a data de devolução do empréstimo.
     * @return A data de devolução do empréstimo.
     */
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * Obtém o ID do empréstimo.
     * @return O ID do empréstimo.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Obtém o livro emprestado.
     * @return O livro emprestado.
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * Obtém o usuário que fez o empréstimo.
     * @return O usuário que fez o empréstimo.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Compara este empréstimo com outro objeto para determinar se são iguais.
     * @param o O objeto a ser comparado.
     * @return True se os objetos são iguais, False caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(livro, that.livro) && Objects.equals(usuario, that.usuario) && Objects.equals(dataEmprestimo, that.dataEmprestimo) && Objects.equals(dataDevolucao, that.dataDevolucao) && Objects.equals(id, that.id) && Objects.equals(status, that.status);
    }
}
