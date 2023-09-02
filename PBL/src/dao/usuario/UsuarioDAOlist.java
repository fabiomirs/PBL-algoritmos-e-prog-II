package dao.usuario;



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

    public Usuario update(Usuario objeto) {
        int index = this.usuarios.indexOf(objeto);
        this.usuarios.set(index, objeto);
        return objeto;
    }

    public void delete(Usuario objeto) {
        this.usuarios.remove(objeto);
    }

    public Usuario buscarporId(Integer id) {
        for (Usuario Usuario : this.usuarios) {
            if (Usuario.getNumIdentificacao() == id) {
                return Usuario;
            }
        }
        return null;
    }

}