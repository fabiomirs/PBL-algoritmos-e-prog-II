package dao.usuario;
import dao.CRUD;
import exceptions.UsuarioException;
import model.Usuario;

/**
 * A interface UsuarioDAO define operações específicas para acesso a dados relacionados aos usuários.
 */
public interface UsuarioDAO extends CRUD<Usuario, UsuarioException> {
}
