package dao.administrador;

import model.Administrador;

import java.util.ArrayList;
import java.util.List;

public class AdmDAOlist implements AdmDAO {
    private List<Administrador> adms;
    private int proximoID;
    public AdmDAOlist() {
        this.adms = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }

    public Administrador create(Administrador objeto) {
        objeto.setNumIdentificacao(this.getProximoID());
        this.adms.add(objeto);
        return objeto;
    }

    public List<Administrador> read() {
        return adms;
    }

    public Administrador update(Administrador objeto) {
        int index = this.adms.indexOf(objeto);
        if (index != -1){
            this.adms.set(index, objeto);
            return objeto;
        }
        return null;
    }
    public void delete(Administrador objeto) {
        this.adms.remove(objeto);
    }

    public Administrador buscarporId(Integer id) {
        for (Administrador Administrador : this.adms) {
            if (Administrador.getNumIdentificacao() == id) {
                return Administrador;
            }
        }
        return null;
    }
}
