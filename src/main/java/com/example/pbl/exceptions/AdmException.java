package com.example.pbl.exceptions;

import com.example.pbl.model.Administrador;

/**
 * A classe AdmException é uma exceção personalizada que pode ser lançada relacionada aos administradores.
 */
public class AdmException extends Exception{

    /**
     * Mensagens estáticas para as exceções
     */
    public static final String DELETE = "Operação de exclusão não realizada.";
    public static final String UPDATE = "Operação de atualização não realizada.";
    public static final String BUSCA_ID = "Não foi encontrado nenhum administrador com este id.";
    private Administrador adm;

    /**
     * Cria uma instância de AdmException com uma mensagem de erro específica e uma referência ao administrador afetado.
     * @param message A mensagem de erro que descreve a exceção.
     * @param adm O administrador relacionado à exceção.
     */
    public AdmException(String message, Administrador adm) {
        super(message);
        this.adm = adm;
    }
}
