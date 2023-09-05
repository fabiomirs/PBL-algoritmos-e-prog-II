package model;

import java.util.Objects;

public class Livro {
    private String titulo;
    private String editora;
    private Integer codigoIsbn;
    private String localizacao;
    private String statusLivro;
    private String autor;
    private String anoPublicacao;
    private Integer id;
    private int proximoId;
    private String categoria;

    public Livro(String titulo, String editora, Integer codigoIsbn, String localizacao,
                 String autor, String anoPublicacao, String categoria) {
        this.titulo = titulo;
        this.editora = editora;
        this.codigoIsbn = codigoIsbn;
        this.localizacao = localizacao;
        this.statusLivro = "Disponivel";
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.id = proximoId;
        this.categoria = categoria;
    }

    public int getProximoId() {
        return proximoId;
    }

    public void setProximoId(int proximoId) {
        this.proximoId = proximoId;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getCodigoIsbn() {
        return codigoIsbn;
    }

    public void setCodigoIsbn(Integer codigoIsbn) {
        this.codigoIsbn = codigoIsbn;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getStatusLivro() {
        return statusLivro;
    }

    public void setStatusLivro(String statusLivro) {
        this.statusLivro = statusLivro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", editora='" + editora + '\'' +
                ", codigoIsbn=" + codigoIsbn +
                ", localizacao='" + localizacao + '\'' +
                ", statusLivro='" + statusLivro + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicacao='" + anoPublicacao + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return proximoId == livro.proximoId && Objects.equals(titulo, livro.titulo) && Objects.equals(editora, livro.editora) && Objects.equals(codigoIsbn, livro.codigoIsbn) && Objects.equals(localizacao, livro.localizacao) && Objects.equals(statusLivro, livro.statusLivro) && Objects.equals(autor, livro.autor) && Objects.equals(anoPublicacao, livro.anoPublicacao) && Objects.equals(id, livro.id) && Objects.equals(categoria, livro.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, editora, codigoIsbn, localizacao, statusLivro, autor, anoPublicacao, id, proximoId, categoria);
    }
}
