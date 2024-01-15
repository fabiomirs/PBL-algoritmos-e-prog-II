package com.example.pbl.dao.bibliotecario;
import com.example.pbl.exceptions.BibliotecarioExcpetion;
import com.example.pbl.model.Bibliotecario;
import com.example.pbl.usuais.FileManager;

import java.util.List;

public class BibliotecarioDAOFile implements BibliotecarioDAO{
    private List<Bibliotecario> bibliotecarios;
    private int proximoID;
    private String nomeArquivo;
    private String nomePasta;

    /**
     * Método responsável por criar o arquivo de bibliotecarios, determinar sua pasta e determinar
     * o id inicial como 0.
     */
    public BibliotecarioDAOFile() {
        this.nomeArquivo = "bibliotecario.dat";
        this.nomePasta = "Bibliotecario";
        this.bibliotecarios = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.bibliotecarios.isEmpty()) {
            this.proximoID = 0;
        } else {
            this.proximoID = this.bibliotecarios.get(this.bibliotecarios.size() - 1).getNumIdentificacao() + 1;
        }

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
        FileManager.salvar(this.bibliotecarios,this.nomeArquivo,this.nomePasta);
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
            FileManager.salvar(this.bibliotecarios,this.nomeArquivo,this.nomePasta);
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
        FileManager.salvar(this.bibliotecarios,this.nomeArquivo,this.nomePasta);
    }

    /**
     * Método usado para deletar a lista de bibliotecários e
     * inicializar o id em 0.
     */
    @Override
    public void deleteMany() {
        this.bibliotecarios.clear();
        FileManager.salvar(this.bibliotecarios,this.nomeArquivo,this.nomePasta);
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

    /**
     * Método que altera o caminho do arquivo bibliotecario para realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaTeste() {
        this.nomePasta = "Bibliotecario Teste";
        this.nomeArquivo = "bibliotecarioTeste.dat";
        this.bibliotecarios = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.bibliotecarios.isEmpty())
            this.proximoID = 0;
        else
            this.proximoID = this.bibliotecarios.get(this.bibliotecarios.size()-1).getNumIdentificacao() + 1;
    }

    /**
     * Método que retorna o caminho do arquivo bibliotecario após realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaPrincipal() {
        this.nomePasta = "Bibliotecario";
        this.nomeArquivo = "bibliotecario.dat";
        this.bibliotecarios = FileManager.ler(this.nomeArquivo,this.nomePasta);

        if(this.bibliotecarios.isEmpty())
            this.proximoID = 0;
        else
            this.proximoID = this.bibliotecarios.get(this.bibliotecarios.size()-1).getNumIdentificacao() + 1;
    }
}
