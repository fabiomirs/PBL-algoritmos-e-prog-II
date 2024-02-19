package com.example.pbl.dao.usuario;
import com.example.pbl.exceptions.UsuarioException;
import com.example.pbl.model.Usuario;
import com.example.pbl.usuais.FileManager;
import java.util.List;

public class UsuarioDAOFile implements UsuarioDAO{
    private List<Usuario> usuarios;
    private int proximoID;
    private String nomePasta;
    private String nomeArquivo;

    /**
     * Método responsável por criar o arquivo de usuários, determinar sua pasta e determinar
     * o id inicial como 0.
     */
    public UsuarioDAOFile() {
        this.nomeArquivo = "usuario.dat";
        this.nomePasta = "Usuario";
        this.usuarios = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.usuarios.isEmpty()) {
            this.proximoID = 0;
        } else {
            this.proximoID = this.usuarios.get(this.usuarios.size() - 1).getNumIdentificacao() + 1;
        }
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
        FileManager.salvar(this.usuarios,this.nomeArquivo,this.nomePasta);
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
            FileManager.salvar(this.usuarios,this.nomeArquivo,this.nomePasta);
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
        FileManager.salvar(this.usuarios,this.nomeArquivo,this.nomePasta);
    }

    /**
     * Método utilizado para excluir a lista de usuários e
     * reinicializar o id como 0.
     */
    @Override
    public void deleteMany() {
        this.usuarios.clear();
        FileManager.salvar(this.usuarios,this.nomeArquivo,this.nomePasta);
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
            if (Usuario.getNumIdentificacao().equals(id)) {
                return Usuario;
            }
        }
        throw new UsuarioException(UsuarioException.BUSCA_ID, null);
    }
    /**
     * Método que altera o caminho do arquivo usuario para realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaTeste() {
        this.nomePasta = "Usuario Teste";
        this.nomeArquivo = "usuarioTeste.dat";
        this.usuarios = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.usuarios.isEmpty())
            this.proximoID = 0;
        else
            this.proximoID = this.usuarios.get(this.usuarios.size()-1).getNumIdentificacao() + 1;
    }

    /**
     * Método que retorna o caminho do arquivo usuario após realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaPrincipal() {
        this.nomePasta = "Usuario";
        this.nomeArquivo = "usuario.dat";
        this.usuarios = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.usuarios.isEmpty())
            this.proximoID = 0;
        else
            this.proximoID = this.usuarios.get(this.usuarios.size()-1).getNumIdentificacao() + 1;
    }
}
