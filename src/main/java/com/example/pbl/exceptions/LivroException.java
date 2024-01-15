package com.example.pbl.exceptions;

import com.example.pbl.model.Livro;

/**
 * A classe LivroException é uma exceção personalizada que pode ser lançada relacionada aos livros.
 */
public class LivroException extends Exception{

    /**
     * Mensagens estáticas para as exceções
     */
    public static final String DELETE = "Operação de exclusão não realizada.";

    public static final String UPDATE = "Operação de atualização não realizada.";

    public static final String BUSCA_ID = "Não foi possível encontrar este livro.";
    public static final String BUSCA_TITULO = "Não foram encontrados livros com este título.";
    public static final String BUSCA_AUTOR = "Não foram encontrados livros escritos por este autor.";
    public static final String BUSCA_ISBN = "Não foram encontrados livros com este código ISBN.";
    public static final String BUSCA_CATEGORIA = "Não foram encontrados livros para a categoria desejada.";
    public static final String RESERVA = "Impossível realizar reserva.";
    public static final String QTD_RESERVA = "Não existem livros com reservas feitas.";
    private Livro livro;

    /**
     * Cria uma instância de LivroException com uma mensagem de erro específica e uma referência ao livro afetado.
     * @param message A mensagem de erro que descreve a exceção.
     * @param livro O livro relacionado à exceção.
     */
    public LivroException(String message, Livro livro) {
        super(message);
        this.livro = livro;
    }
}
