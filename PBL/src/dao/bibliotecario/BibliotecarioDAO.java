package dao.bibliotecario;
import dao.CRUD;
import exceptions.BibliotecarioExcpetion;
import model.Bibliotecario;

/**
 * A interface BibliotecarioDAO define operações específicas para acesso a dados relacionados aos bibliotecários.
 */
public interface BibliotecarioDAO extends CRUD<Bibliotecario, BibliotecarioExcpetion> {
}
