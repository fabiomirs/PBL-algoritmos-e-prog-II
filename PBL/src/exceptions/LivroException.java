package exceptions;

import model.Livro;

public class LivroException extends Exception{

    /** Mensagens estáticas para as exceções*/
    public static final String DELETE = "Operação de exclusão não realizada.";

    public static final String UPDATE = "Operação de atualização não realizada.";

    public static final String BUSCA_ID = "Não foi possível encontrar este livro.";
    public static final String BUSCA_TITULO = "Não foram encontrados livros com este título.";
    public static final String BUSCA_AUTOR = "Não foram encontrados livros escritos por este autor.";
    public static final String BUSCA_ISBN = "Não foram encontrados livros com este código ISBN.";
    public static final String BUSCA_CATEGORIA = "Não foram encontrados livros para a categoria desejada.";
    private Livro livro;

    public LivroException(String message, Livro livro) {
        super(message);
        this.livro = livro;
    }
}
