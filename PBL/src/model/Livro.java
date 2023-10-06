package model;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * A classe Livro representa um livro da biblioteca.
 */
public class Livro {
    /**
     * Título correspondente ao livro.
     */
    private String titulo;
    /**
     * Editora correspondente ao livro.
     */
    private String editora;
    /**
     * Código ISBN correspondente ao livro.
     */
    private Integer codigoIsbn;
    /**
     * Localização correspondente ao livro.
     */
    private String localizacao;
    /**
     * Status que está o livro.
     */
    private String statusLivro;
    /**
     * Autor correspondente ao livro.
     */
    private String autor;
    /**
     * Ano em que o livro foi publicado.
     */
    private String anoPublicacao;
    /**
     * Número de identificação correspondente ao livro.
     */
    private Integer id;
    /**
     * Id que será associado ao próximo livro correspondente .
     */
    private int proximoId;
    /**
     * Categoria correspondente ao livro.
     */
    private String categoria;
    /**
     * Fila de reservas correspondente ao livro.
     */
    private Queue<Usuario> reservas = new LinkedList<>();

    /**
     * Adiciona uma reserva de um livro por um usuário.
     * @param objeto O usuário que deseja reservar o livro.
     */
    public void addReserva(Usuario objeto){
        reservas.offer(objeto);
    }

    /**
     * Obtém o próximo usuário na fila de reservas sem removê-lo.
     * @return O próximo usuário na fila de reservas.
     */
    public Usuario proxReserva(){
        return reservas.peek();
    }

    /**
     * Remove o próximo usuário da fila de reservas.
     */
    public void tiraFilareserva(){
        reservas.poll();
    }

    /**
     * Obtém a fila de reservas deste livro.
     * @return A fila de reservas.
     */
    public Queue<Usuario> getReservas() {
        return reservas;
    }

    /**
     * Construtor para criar uma instância de Livro.
     * @param titulo O título do livro.
     * @param editora A editora do livro.
     * @param codigoIsbn O código ISBN do livro.
     * @param localizacao A localização física do livro na biblioteca.
     * @param autor O autor do livro.
     * @param anoPublicacao O ano de publicação do livro.
     * @param categoria A categoria do livro.
     */
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

    /**
     * Construtor para criar uma instância de Livro com um ID específico.
     * @param titulo O título do livro.
     * @param editora A editora do livro.
     * @param codigoIsbn O código ISBN do livro.
     * @param localizacao A localização física do livro na biblioteca.
     * @param autor O autor do livro.
     * @param anoPublicacao O ano de publicação do livro.
     * @param categoria A categoria do livro.
     * @param id O ID único do livro.
     */
    public Livro(String titulo, String editora, Integer codigoIsbn, String localizacao,
                 String autor, String anoPublicacao, String categoria, int id) {
        this.titulo = titulo;
        this.editora = editora;
        this.codigoIsbn = codigoIsbn;
        this.localizacao = localizacao;
        this.statusLivro = "Disponivel";
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.id = id;
        this.categoria = categoria;
    }

    /**
     * Obtém o próximo ID disponível para livros.
     * @return O próximo ID disponível.
     */
    public int getProximoId() {
        return proximoId;
    }

    /**
     * Determina o próximo ID disponível para livros.
     * @param proximoId O próximo ID disponível determinado.
     */
    public void setProximoId(int proximoId) {
        this.proximoId = proximoId;
    }

    /**
     * Obtém a categoria do livro.
     * @return A categoria do livro.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Determina a categoria do livro.
     * @param categoria A categoria do livro determinada.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtém o ID do livro.
     * @return O ID do livro.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Determina o ID do livro.
     * @param id O ID do livro determinado.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtém o título do livro.
     * @return O título do livro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Determina o título do livro.
     * @param titulo O título do livro determinado.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém a editora do livro.
     * @return A editora do livro.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Determina a editora do livro.
     * @param editora A editora do livro determinada.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Obtém o código ISBN do livro.
     * @return O código ISBN do livro.
     */
    public Integer getCodigoIsbn() {
        return codigoIsbn;
    }

    /**
     * Determina o código ISBN do livro.
     * @param codigoIsbn O código ISBN do livro determinado.
     */
    public void setCodigoIsbn(Integer codigoIsbn) {
        this.codigoIsbn = codigoIsbn;
    }

    /**
     * Obtém a localização do livro.
     * @return A localização.
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * Determina a localização do livro.
     * @param localizacao A localização determinada.
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Obtém o status do livro.
     * @return O status do livro.
     */
    public String getStatusLivro() {
        return statusLivro;
    }

    /**
     * Determina o status do livro.
     * @param statusLivro O status determinado.
     */
    public void setStatusLivro(String statusLivro) {
        this.statusLivro = statusLivro;
    }

    /**
     * Obtém o autor do livro.
     * @return O autor.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Determina o autor do livro.
     * @param autor O autor determinado.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtém o ano de publicação do livro.
     * @return O ano de publicação do livro.
     */
    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    /**
     * Determina o ano de publicação do livro.
     * @param anoPublicacao O ano de publicação determinado.
     */
    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * @param o Objeto a ser comparado.
     * @return True ou False dependendo do resultado da comparação.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return proximoId == livro.proximoId && Objects.equals(titulo, livro.titulo) && Objects.equals(editora, livro.editora) && Objects.equals(codigoIsbn, livro.codigoIsbn) && Objects.equals(localizacao, livro.localizacao) && Objects.equals(statusLivro, livro.statusLivro) && Objects.equals(autor, livro.autor) && Objects.equals(anoPublicacao, livro.anoPublicacao) && Objects.equals(id, livro.id) && Objects.equals(categoria, livro.categoria) && Objects.equals(reservas, livro.reservas);
    }
}
