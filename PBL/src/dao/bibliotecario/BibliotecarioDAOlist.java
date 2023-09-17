package dao.bibliotecario;

import exceptions.BibliotecarioExcpetion;
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

    public Bibliotecario update(Bibliotecario objeto) throws BibliotecarioExcpetion{
        int index = this.bibliotecarios.indexOf(objeto);
        if (index != -1){
            this.bibliotecarios.set(index, objeto);
            return objeto;
        }
        else {
            throw new BibliotecarioExcpetion(BibliotecarioExcpetion.UPDATE, objeto);
        }
    }

    public void delete(Bibliotecario objeto) throws BibliotecarioExcpetion {
        boolean remove = this.bibliotecarios.remove(objeto);
        if (!remove){
            throw new BibliotecarioExcpetion(BibliotecarioExcpetion.DELETE, objeto);
        }
    }

    public Bibliotecario buscarporId(Integer id) throws BibliotecarioExcpetion{
        for (Bibliotecario Bibliotecario : this.bibliotecarios) {
            if (Bibliotecario.getNumIdentificacao() == id) {
                return Bibliotecario;
            }
        }
        throw new BibliotecarioExcpetion(BibliotecarioExcpetion.BUSCA_ID, null);
    }
}
