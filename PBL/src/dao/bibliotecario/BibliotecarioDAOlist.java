package dao.bibliotecario;
import exceptions.BibliotecarioExcpetion;
import model.Bibliotecario;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe BibliotecarioDAOlist implementa os métodos relacionados a interface BibliotecarioDAO.
 */
public class BibliotecarioDAOlist implements BibliotecarioDAO{
    private List<Bibliotecario> bibliotecarios;
    private int proximoID;

    /**
     * Método usado para criar a lista de bibliotecários e
     * inicializar o id em 0.
     */
    public BibliotecarioDAOlist() {
        this.bibliotecarios = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * @return Retorna o id que será utilizado para a criação do usuário.
     */
    private int getProximoID() {
        return this.proximoID++;
    }

    /**
     * Método responsável por atribuir um identificador ao objeto bibliotecario e
     * adicioná-lo a lista de bibliotecários.
     * @param objeto Objeto bibliotecario que será criado.
     * @return O objeto criado.
     */
    @Override
    public Bibliotecario create(Bibliotecario objeto) {
        objeto.setNumIdentificacao(this.getProximoID());
        this.bibliotecarios.add(objeto);
        return objeto;
    }

    /**
     * Método que retorna a lista de bibliotecários.
     * @return A lista de bibliotecários existente.
     */
    @Override
    public List<Bibliotecario> read() {
        return bibliotecarios;
    }

    /**
     * Método usado para atualizar os dados de um bibliotecário.
     * @param objeto Bibliotecário que deseja atualizar.
     * @return Bibliotecário atualizado.
     * @throws BibliotecarioExcpetion Exceção que pode ser lançada caso
     * não encontre o bibliotecário.
     */
    @Override
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

    /**
     * Método usado para deletar um bibliotecário da lista.
     * @param objeto Bibliotecario que deseja deletar.
     * @throws BibliotecarioExcpetion Exceção que pode ser lançada caso
     * não encontre o bibliotecário.
     */
    @Override
    public void delete(Bibliotecario objeto) throws BibliotecarioExcpetion {
        boolean remove = this.bibliotecarios.remove(objeto);
        if (!remove){
            throw new BibliotecarioExcpetion(BibliotecarioExcpetion.DELETE, objeto);
        }
    }

    /**
     * Método usado para deletar a lista de bibliotecários e
     * inicializar o id em 0.
     */
    @Override
    public void deleteMany() {
        this.bibliotecarios = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * Método usado para buscar um bibliotecário por id.
     * @param id Id do bibliotecário que deseja.
     * @return O bibliotecário encontrado.
     * @throws BibliotecarioExcpetion Exceção que pode ser lançada caso
     * não encontre o bibliotecário.
     */
    @Override
    public Bibliotecario buscarporId(Integer id) throws BibliotecarioExcpetion{
        for (Bibliotecario Bibliotecario : this.bibliotecarios) {
            if (Bibliotecario.getNumIdentificacao() == id) {
                return Bibliotecario;
            }
        }
        throw new BibliotecarioExcpetion(BibliotecarioExcpetion.BUSCA_ID, null);
    }

    @Override
    public void alteraParaPastaTeste() {

    }

    @Override
    public void alteraParaPastaPrincipal() {

    }
}
