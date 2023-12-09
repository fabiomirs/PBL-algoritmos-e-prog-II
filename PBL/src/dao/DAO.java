package dao;
import dao.administrador.AdmDAO;
import dao.administrador.AdmDAOFile;
import dao.bibliotecario.BibliotecarioDAO;
import dao.bibliotecario.BibliotecarioDAOFile;
import dao.emprestimo.EmprestimoDAO;
import dao.emprestimo.EmprestimoDAOFile;
import dao.livro.LivroDAO;
import dao.livro.LivroDAOFile;
import dao.usuario.UsuarioDAO;
import dao.usuario.UsuarioDAOFile;

/**
 * A classe DAO é responsável por fornecer o acesso às implementações das interfaces DAO do sistema.
 */
public class DAO {
    private static LivroDAO livroDAO;

    /**
     * Obtém a instância do LivroDAO para ter acesso aos métodos lá implementados.
     * @return A instância de LivroDAO.
     */
    public static LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            livroDAO = new LivroDAOFile();
        }
        return livroDAO;
    }

    private static UsuarioDAO usuarioDAO;

    /**
     * Obtém a instância do UsuarioDAO para ter acesso aos métodos lá implementados.
     * @return A instância de UsuarioDAO.
     */
    public static UsuarioDAO getUsuarioDAO(){
        if (usuarioDAO == null){
            usuarioDAO = new UsuarioDAOFile();
        }
        return usuarioDAO;
    }

    private static EmprestimoDAO emprestimoDAO;

    /**
     * Obtém a instância do EmprestimoDAO para ter acesso aos métodos lá implementados.
     * @return A instância de EmprestimoDAO.
     */
    public static EmprestimoDAO getEmprestimoDAO(){
        if (emprestimoDAO == null){
            emprestimoDAO = new EmprestimoDAOFile();
        }
        return emprestimoDAO;
    }

    private static BibliotecarioDAO bibliotecarioDAO;

    /**
     * Obtém a instância do BibliotecarioDAO para ter acesso aos métodos lá implementados.
     * @return A instância de BibliotecarioDAO.
     */
    public static BibliotecarioDAO getBibliotecarioDAO(){
        if (bibliotecarioDAO == null){
            bibliotecarioDAO = new BibliotecarioDAOFile();
        }
        return bibliotecarioDAO;
    }

    private static AdmDAO admDAO;

    /**
     * Obtém a instância do AdmDAO para ter acesso aos métodos lá implementados.
     * @return A instância de AdmDAO.
     */
    public static AdmDAO getAdmDAO(){
        if (admDAO == null){
            admDAO = new AdmDAOFile();
        }
        return admDAO;
    }


}
