package com.example.pbl.exceptions;

import com.example.pbl.model.Usuario;

/**
 * A classe UsuarioException é uma exceção personalizada que pode ser lançada relacionada aos usuários.
 */
public class UsuarioException extends Exception{

        /**
         * Mensagens estáticas para as exceções
         **/
        public static final String DELETE = "Operação de exclusão não realizada.";
        public static final String UPDATE = "Operação de atualização não realizada.";
        public static final String BUSCA_ID = "Não foi possível encontrar este usuário.";
        private Usuario usuario;

    /**
     * Cria uma instância de UsuarioException com uma mensagem de erro específica e uma referência ao usuário afetado.
     * @param message A mensagem de erro que descreve a exceção.
     * @param usuario O bibliotecario relacionado à exceção.
     */
        public UsuarioException(String message, Usuario usuario) {
            super(message);
            this.usuario = usuario;
        }
    }


