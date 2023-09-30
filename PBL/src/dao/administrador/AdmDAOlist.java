package dao.administrador;
import exceptions.AdmException;
import model.Administrador;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe AdmDAOlist implementa os métodos relacionados a interface AdmDAO.
 */
public class AdmDAOlist implements AdmDAO {
    private List<Administrador> adms;
    private int proximoID;

    /**
     * Método usado para criar a lista de administradores e
     * inicializar o id em 0.
     */
    public AdmDAOlist() {
        this.adms = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     *
     * @return Retorna o id que será utilizado para a criação do usuário.
     */
    private int getProximoID() {
        return this.proximoID++;
    }

    /**
     * Método responsável por atribuir um identificador ao objeto administrador e
     * adicioná-lo a lista de administradores.
     * @param objeto Objeto administrador que será criado.
     * @return O objeto criado.
     */
    public Administrador create(Administrador objeto) {
        objeto.setNumIdentificacao(this.getProximoID());
        this.adms.add(objeto);
        return objeto;
    }

    /**
     * Método que retorna a lista de administradores.
     * @return A lista de administradores existente.
     */
    public List<Administrador> read() {
        return adms;
    }

    /**
     * Método usado para atualizar os dados de um administrador.
     * @param objeto Administrador que deseja atualizar.
     * @return Administrador atualizado.
     * @throws AdmException Exceção que pode ser lançada caso
     * não encontre o administrador.
     */
    public Administrador update(Administrador objeto) throws AdmException {
        int index = this.adms.indexOf(objeto);
        if (index != -1){
            this.adms.set(index, objeto);
            return objeto;
        }
        throw new AdmException(AdmException.UPDATE, objeto);

    }

    /**
     * Método usado para deletar um administrador da lista.
     * @param objeto Administrador que deseja deletar.
     * @throws AdmException Exceção que pode ser lançada caso
     * não encontre o administrador.
     */
    public void delete(Administrador objeto) throws AdmException{
        boolean remove = this.adms.remove(objeto);
        if (!remove){
            throw new AdmException(AdmException.DELETE, objeto);
        }

    }

    /**
     * Método usado para deletar a lista de dministradores e
     * inicializar o id em 0.
     */
    @Override
    public void deleteMany() {
        this.adms = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * Método usado para buscar um administrador por id.
     * @param id Id do administrador que deseja.
     * @return O administrador encontrado.
     * @throws AdmException Exceção que pode ser lançada caso
     * não encontre o administrador.
     */
    public Administrador buscarporId(Integer id) throws AdmException{
        for (Administrador Administrador : this.adms) {
            if (Administrador.getNumIdentificacao() == id) {
                return Administrador;
            }
        }
        throw new AdmException(AdmException.BUSCA_ID, null);
    }
}
