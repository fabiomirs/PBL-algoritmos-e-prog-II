package exceptions;

import model.Bibliotecario;

/**
 * A classe BibliotecarioException é uma exceção personalizada que pode ser lançada relacionada aos bibliotecarios.
 */
public class BibliotecarioExcpetion extends Exception{

    /**
     * Mensagens estáticas para as exceções
     */
    public static final String DELETE = "Operação de exclusão não realizada.";
    public static final String UPDATE = "Operação de atualização não realizada.";
    public static final String BUSCA_ID = "Não foi encontrado nenhum bibliotecário com este id.";
    private Bibliotecario bibliotecario;

    /**
     * Cria uma instância de BibliotecarioException com uma mensagem de erro específica e uma referência ao bibliotecério afetado.
     * @param message A mensagem de erro que descreve a exceção.
     * @param bibliotecario O bibliotecario relacionado à exceção.
     */
    public BibliotecarioExcpetion(String message, Bibliotecario bibliotecario) {
        super(message);
        this.bibliotecario = bibliotecario;
    }
}
