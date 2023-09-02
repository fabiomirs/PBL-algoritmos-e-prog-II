package dao;

import dao.livro.LivroDAO;
import dao.livro.LivroDAOlist;
import dao.usuario.UsuarioDAO;
import dao.usuario.UsuarioDAOlist;

public class DAO {
    private static LivroDAO livroDAO;
    public static LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            livroDAO = new LivroDAOlist();
        }
        return livroDAO;
    }

    private static UsuarioDAO usuarioDAO;
    public static UsuarioDAO getUsuarioDAO(){
        if (usuarioDAO == null){
            usuarioDAO = new UsuarioDAOlist();
        }
        return usuarioDAO;
    }

}
