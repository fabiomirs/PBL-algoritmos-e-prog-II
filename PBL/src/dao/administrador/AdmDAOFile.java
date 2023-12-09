package dao.administrador;
import exceptions.AdmException;
import model.Administrador;
import usuais.FileManager;
import java.util.List;

/**
 * A classe AdmDAOFile implementa os métodos relacionados a interface AdmDAO.
 */
public class AdmDAOFile implements AdmDAO{
    private List<Administrador> adms;
    private int proximoID;
    private String nomeArquivo;
    private String nomePasta;

    /**
     * Método responsável por criar o arquivo de adms, determinar sua pasta e determinar
     * o id inicial como 0.
     */
    public AdmDAOFile() {
        this.nomeArquivo = "administrador.dat";
        this.nomePasta = "Administrador";
        this.adms = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.adms.isEmpty()) {
            this.proximoID = 0;
        } else {
            this.proximoID = this.adms.get(this.adms.size() - 1).getNumIdentificacao() + 1;
        }
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
        FileManager.salvar(this.adms,this.nomeArquivo,this.nomePasta);
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
            FileManager.salvar(this.adms,this.nomeArquivo,this.nomePasta);
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
        FileManager.salvar(this.adms,this.nomeArquivo,this.nomePasta);

    }

    /**
     * Método usado para deletar a lista de dministradores e
     * inicializar o id em 0.
     */
    @Override
    public void deleteMany() {
        this.adms.clear();
        FileManager.salvar(this.adms,this.nomeArquivo,this.nomePasta);
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

    /**
     * Método que altera o caminho do arquivo administrador para realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaTeste() {
        this.nomePasta = "Administrador Teste";
        this.nomeArquivo = "administradorTeste.dat";
        this.adms = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.adms.isEmpty())
            this.proximoID = 0;
        else
            this.proximoID = this.adms.get(this.adms.size()-1).getNumIdentificacao() + 1;
    }

    /**
     * Método que retorna o caminho do arquivo administrador após realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaPrincipal() {
        this.nomePasta = "Administrador";
        this.nomeArquivo = "administrador.dat";
        this.adms = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.adms.isEmpty())
            this.proximoID = 0;
        else
            this.proximoID = this.adms.get(this.adms.size()-1).getNumIdentificacao() + 1;
    }
}
