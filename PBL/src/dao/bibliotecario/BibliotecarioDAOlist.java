package dao.bibliotecario;

import model.Bibliotecario;

import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAOlist implements BibliotecarioDAO{
    private List<Bibliotecario> bibliotecarios;
    private int proximoID;
    public BibliotecarioDAOlist() {
        this.bibliotecarios = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }

    public Bibliotecario create(Bibliotecario objeto) {
        objeto.setNumIdentificacao(this.getProximoID());
        this.bibliotecarios.add(objeto);
        return objeto;
    }

    public List<Bibliotecario> read() {
        return bibliotecarios;
    }

    public Bibliotecario update(Bibliotecario objeto) {
        int index = this.bibliotecarios.indexOf(objeto);
        if (index != -1){
            this.bibliotecarios.set(index, objeto);
            return objeto;
        }
        return null;
    }

    public void delete(Bibliotecario objeto) {
        this.bibliotecarios.remove(objeto);
    }

    public Bibliotecario buscarporId(Integer id) {
        for (Bibliotecario Bibliotecario : this.bibliotecarios) {
            if (Bibliotecario.getNumIdentificacao() == id) {
                return Bibliotecario;
            }
        }
        return null;
    }
}
