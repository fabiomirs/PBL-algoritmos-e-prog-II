package dao.administrador;

import exceptions.AdmException;
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

    public Administrador update(Administrador objeto) throws AdmException {
        int index = this.adms.indexOf(objeto);
        if (index != -1){
            this.adms.set(index, objeto);
            return objeto;
        }
        throw new AdmException(AdmException.UPDATE, objeto);

    }
    public void delete(Administrador objeto) throws AdmException{
        boolean remove = this.adms.remove(objeto);
        if (!remove){
            throw new AdmException(AdmException.DELETE, objeto);
        }

    }

    @Override
    public void deleteMany() {
        this.adms = new ArrayList<>();
        this.proximoID = 0;
    }

    public Administrador buscarporId(Integer id) throws AdmException{
        for (Administrador Administrador : this.adms) {
            if (Administrador.getNumIdentificacao() == id) {
                return Administrador;
            }
        }
        throw new AdmException(AdmException.BUSCA_ID, null);
    }
}
