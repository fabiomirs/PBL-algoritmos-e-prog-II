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
    public void infoLivro(){
        System.out.println("Informações sobre o livro:");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Editora: " + editora);
        System.out.println("Código ISBN: " + codigoIsbn);
        System.out.println("Localização: " + localizacao);
        System.out.println("Status do Livro: " + statusLivro);
        System.out.println("Autor: " + autor);
        System.out.println("Ano de Publicação: " + anoPublicacao);
    }
    public void attInfoLivro(String novoTitulo, String novaEditora, Integer novoCodigoIsbn, String novaLocalizacao,
                             String novoStatusLivro, String novoAutor, String novoAnoPublicacao) {
        System.out.println("Atualizando informações do livro (ID: " + id + "):");

        if (novoTitulo != null) {
            titulo = novoTitulo;
            System.out.println("Novo Título: " + novoTitulo);
        }

        if (novaEditora != null) {
            editora = novaEditora;
            System.out.println("Nova Editora: " + novaEditora);
        }

        if (novoCodigoIsbn != null) {
            codigoIsbn = novoCodigoIsbn;
            System.out.println("Novo Código ISBN: " + novoCodigoIsbn);
        }

        if (novaLocalizacao != null) {
            localizacao = novaLocalizacao;
            System.out.println("Nova Localização: " + novaLocalizacao);
        }

        if (novoStatusLivro != null) {
            statusLivro = novoStatusLivro;
            System.out.println("Novo Status do Livro: " + novoStatusLivro);
        }

        if (novoAutor != null) {
            autor = novoAutor;
            System.out.println("Novo Autor: " + novoAutor);
        }

        if (novoAnoPublicacao != null) {
            anoPublicacao = novoAnoPublicacao;
            System.out.println("Novo Ano de Publicação: " + novoAnoPublicacao);
        }
    }

}
