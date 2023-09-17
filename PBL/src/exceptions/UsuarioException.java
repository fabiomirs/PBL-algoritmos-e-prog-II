package exceptions;

import model.Usuario;

public class UsuarioException extends Exception{

        /** Mensagens estáticas para as exceções*/
        public static final String DELETE = "Operação de exclusão não realizada.";
        public static final String UPDATE = "Operação de atualização não realizada.";
        public static final String BUSCA_ID = "Não foi possível encontrar este usuário.";
        private Usuario usuario;

        public UsuarioException(String message, Usuario usuario) {
            super(message);
            this.usuario = usuario;
        }
    }


