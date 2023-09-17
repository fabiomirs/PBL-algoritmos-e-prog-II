package exceptions;

import model.Administrador;


public class AdmException extends Exception{
    /** Mensagens estáticas para as exceções*/
    public static final String DELETE = "Operação de exclusão não realizada.";
    public static final String UPDATE = "Operação de atualização não realizada.";
    public static final String BUSCA_ID = "Não foi encontrado nenhum administrador com este id.";
    private Administrador adm;

    public AdmException(String message, Administrador adm) {
        super(message);
        this.adm = adm;
    }
}
