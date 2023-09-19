package dao.usuario;



import exceptions.UsuarioException;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOlist implements UsuarioDAO {
    private List<Usuario> usuarios;
    private int proximoID;
    public UsuarioDAOlist() {
        this.usuarios = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }

    public Usuario create(Usuario objeto) {
        objeto.setNumIdentificacao(this.getProximoID());
        this.usuarios.add(objeto);
        return objeto;
    }

    public List<Usuario> read() {
        return usuarios;
    }

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

    public void delete(Usuario objeto) throws UsuarioException{
        boolean remocao = this.usuarios.remove(objeto);
        if (!remocao){
            throw new UsuarioException(UsuarioException.DELETE, objeto);
        }
    }

    @Override
    public void deleteMany() {
        this.usuarios = new ArrayList<>();
        this.proximoID = 0;
    }

    public Usuario buscarporId(Integer id) throws UsuarioException{
        for (Usuario Usuario : this.usuarios) {
            if (Usuario.getNumIdentificacao() == id) {
                return Usuario;
            }
        }
        throw new UsuarioException(UsuarioException.BUSCA_ID, null);
    }

}