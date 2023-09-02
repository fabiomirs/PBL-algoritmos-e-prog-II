package model;

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

    public Livro(String titulo, String editora, Integer codigoIsbn, String localizacao,
                 String statusLivro, String autor, String anoPublicacao) {
        this.titulo = titulo;
        this.editora = editora;
        this.codigoIsbn = codigoIsbn;
        this.localizacao = localizacao;
        this.statusLivro = statusLivro;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.id = proximoId;
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
}
