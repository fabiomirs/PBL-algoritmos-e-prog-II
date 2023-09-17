package exceptions;

import model.Bibliotecario;

public class BibliotecarioExcpetion extends Exception{
    /** Mensagens estáticas para as exceções*/
    public static final String DELETE = "Operação de EXCLUSÃO não realizada.";
    public static final String UPDATE = "Operação de ATUALIZAÇÃO não realizada.";
    public static final String BUSCA_ID = "Não foi encontrado nenhum bibliotecário com este id.";
    private Bibliotecario bibliotecario;

    public BibliotecarioExcpetion(String message, Bibliotecario bibliotecario) {
        super(message);
        this.bibliotecario = bibliotecario;
    }
}
