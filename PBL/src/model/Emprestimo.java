package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Integer id;
    public Emprestimo(Livro livro,Usuario usuario){
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = this.dataEmprestimo.plusDays(7);
        this.id = usuario.getNumIdentificacao();
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public Integer getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Emprestimo{" +
                "dataEmprestimo=" + dataEmprestimo.format(formatar) +
                ", dataDevolucao=" + dataDevolucao.format(formatar) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(livro, that.livro) && Objects.equals(usuario, that.usuario) && Objects.equals(dataEmprestimo, that.dataEmprestimo) && Objects.equals(dataDevolucao, that.dataDevolucao) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, usuario, dataEmprestimo, dataDevolucao, id);
    }
}


