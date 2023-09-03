package dao;

import dao.administrador.AdmDAO;
import dao.administrador.AdmDAOlist;
import dao.bibliotecario.BibliotecarioDAO;
import dao.bibliotecario.BibliotecarioDAOlist;
import dao.emprestimo.EmprestimoDAO;
import dao.emprestimo.EmprestimoDAOlist;
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

    private static EmprestimoDAO emprestimoDAO;
    public static EmprestimoDAO getEmprestimoDAODAO(){
        if (emprestimoDAO == null){
            emprestimoDAO = new EmprestimoDAOlist();
        }
        return emprestimoDAO;
    }

    private static BibliotecarioDAO bibliotecarioDAO;
    public static BibliotecarioDAO getBibliotecarioDAO(){
        if (bibliotecarioDAO == null){
            bibliotecarioDAO = new BibliotecarioDAOlist();
        }
        return bibliotecarioDAO;
    }

    private static AdmDAO admDAO;
    public static AdmDAO getAdmDAO(){
        if (admDAO == null){
            admDAO = new AdmDAOlist();
        }
        return admDAO;
    }


}
