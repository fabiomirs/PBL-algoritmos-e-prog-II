package dao.usuario;
import exceptions.UsuarioException;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe UsuarioDAOlist implementa os métodos relacionados a interface UsuarioDAO.
 */
public class UsuarioDAOlist implements UsuarioDAO {
    private List<Usuario> usuarios;
    private int proximoID;

    /**
     * Método responsável por criar a lista de usuários e determinar
     * o id inicial como 0.
     */
    public UsuarioDAOlist() {
        this.usuarios = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * @return Retorna o id que será utilizado para a criação do usuário.
     */
    private int getProximoID() {
        return this.proximoID++;
    }

    /**
     * Método responsável por atribuir um identificador ao objeto Usuario e
     * adicioná-lo a lista de usuários.
     * @param objeto Objeto Usuario que será criado.
     * @return O objeto criado.
     */
    @Override
    public Usuario create(Usuario objeto) {
        objeto.setNumIdentificacao(this.getProximoID());
        this.usuarios.add(objeto);
        return objeto;
    }

    /**
     * Método que retorna a lista de usuários.
     * @return A lista de usuarios existente.
     */
    @Override
    public List<Usuario> read() {
        return usuarios;
    }

    /**
     * Método utilizado para atualizar um objeto Usuario.
     * @param objeto Objeto Usuario que vai ser atualizado.
     * @return O objeto atualizado.
     * @throws UsuarioException Exceção que pode ser lançada, caso o usuario não exista.
     */
    @Override
    public Usuario update(Usuario objeto) throws UsuarioException{
        int index = this.usuarios.indexOf(objeto);
        if (index != -1){
            this.usuarios.set(index, objeto);
            return objeto;
        }
        else {
            throw new UsuarioException(UsuarioException.UPDATE, objeto);
        }
    }

    /**
     * Método utilizado para deletar um objeto Usuario da lista existente.
     * @param objeto Objeto que vai ser deletado.
     * @throws UsuarioException Exceção que pode ser lançada, caso o usuario não exista na lista.
     */
    @Override
    public void delete(Usuario objeto) throws UsuarioException{
        boolean remocao = this.usuarios.remove(objeto);
        if (!remocao){
            throw new UsuarioException(UsuarioException.DELETE, objeto);
        }
    }

    /**
     * Método utilizado para excluir a lista de usuários e
     * reinicializar o id como 0.
     */
    @Override
    public void deleteMany() {
        this.usuarios = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * Método usado para buscar um usuário por id.
     * @param id O id a ser buscado.
     * @return O objeto encontrado.
     * @throws UsuarioException Exceção que pode ser lançada, caso o id não exista na lista.
     */
    @Override
    public Usuario buscarporId(Integer id) throws UsuarioException{
        for (Usuario Usuario : this.usuarios) {
            if (Usuario.getNumIdentificacao() == id) {
                return Usuario;
            }
        }
        throw new UsuarioException(UsuarioException.BUSCA_ID, null);
    }

}