package dao.administrador;

import dao.DAO;
import exceptions.AdmException;

import model.Administrador;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe criada para realizar os testes relacionados ao AdmDAOlist.
 */
class AdmDAOTest {

    /**
     * Representa o primeiro administrador de teste.
     */
    Administrador Admteste;
    /**
     * Representa o segundo administrador de teste.
     */
    Administrador Admteste2;
    /**
     * Representa o terceiro administrador de teste.
     */
    Administrador Admteste3;

    /**
     * Executa ANTES de cada teste
     */
    @BeforeEach
    void setUp() {
        DAO.getAdmDAO().alteraParaPastaTeste();
        Admteste = DAO.getAdmDAO().create(new Administrador("Admteste", "administrador", 22222));
        Admteste2 = DAO.getAdmDAO().create(new Administrador("Admteste2", "administrador", 11111));
        Admteste3 = DAO.getAdmDAO().create(new Administrador("Admteste3", "administrador", 33333));
    }

    /**
     * Executa DEPOIS de cada teste
     */
    @AfterEach
    void tearDown() {
        DAO.getAdmDAO().deleteMany();
        DAO.getAdmDAO().alteraParaPastaPrincipal();
    }

    /**
     * Realizando o teste para criação de um administrador, para isso compara-se um objeto já existente com o que será criado.
     */
    @Test
    void create() {
        Administrador esperado = new Administrador("FabioAdm", "administrador", 7777, 3);
        Administrador atual = DAO.getAdmDAO().create(new Administrador("FabioAdm", "administrador", 7777));

        assertEquals(esperado, atual);
    }

    /**
     * Verificando um caso de atualização de dado válido para o administrador.
     * Compara como o objeto deve estar com o objeto depois de passar pelas atualizações.
     * @throws AdmException Exceção que pode ser lançada, como é um teste que vai dar certo ela não se aplica.
     */
    @Test
    void update() throws AdmException{
        Admteste.setNome("joao");
        Admteste.setSenhaAcesso(99999);
        Administrador atual = DAO.getAdmDAO().update(Admteste);
        assertEquals(Admteste, atual);


        Admteste2.setNome("carlos");
        Admteste2.setNumIdentificacao(9);
        Administrador atual_2 = DAO.getAdmDAO().update(Admteste2);
        assertEquals(Admteste2, atual_2);

        Admteste3.setNome("felipe");
        Admteste3.setCargo("Bibliotecário");
        Admteste3.setSenhaAcesso(45598);
        Administrador atual_3 = DAO.getAdmDAO().update(Admteste3);
        assertEquals(Admteste3, atual_3);
    }

    /**
     * Teste para realizar uma atualização de dado inválida e gerar um caso de erro.
     * @throws AdmException Exceção lançada ao acontecer o erro de atualização.
     */
    @Test
    void failUpdate() throws AdmException {
        try {
            Administrador admteste = new Administrador("Fabio", "Administrador", 98513256);
            admteste.setNome("Fábio fail");
            admteste.setSenhaAcesso(12345);
            DAO.getAdmDAO().update(admteste);
        }catch (AdmException e) {
            assertEquals(AdmException.UPDATE, e.getMessage());
        }
    }

    /**
     * Método de teste para realizar a exclusão de um administrador e garantir a veracidade
     * comparando a quantidade de elementos que existiam antes e depois da exclusão ser feita.
     * @throws AdmException Exceção que pode ser lançada, como é um teste que vai dar certo ela não se aplica.
     */
    @Test
    void delete() throws AdmException {
        int tamanho_esperado = DAO.getAdmDAO().read().size();
        DAO.getAdmDAO().delete(Admteste);
        assertEquals(tamanho_esperado-1, DAO.getAdmDAO().read().size());
    }

    /**
     * Teste realizado tentando excluir um administrador inválido.
     * @throws AdmException Exceção lançada ao acontecer o erro de exclusão.
     */
    @Test
    void failDelete() throws AdmException{
        try {
            DAO.getAdmDAO().delete(new Administrador("Fábio", "administrador", 00000));
            fail("Uma exceção deveria ser gerada!!");
        } catch (AdmException e) {
            assertEquals(AdmException.DELETE, e.getMessage());
        }

    }

    /**
     * Método de teste usado para fazer a leitura e verificar se o tamanho da lista
     * de administradores corresponde a quantidade que é criada no setup().
     */
    @Test
    void read() {
        DAO.getAdmDAO().read();
        assertEquals(3, DAO.getAdmDAO().read().size());
    }

    /**
     * Teste para verificar se ao excluir todos os dados salvos o tamanho da lista de
     * administradores fica zerada.
     */
    @Test
    void deleteMany() {
        DAO.getAdmDAO().deleteMany();
        assertEquals(0, DAO.getAdmDAO().read().size());
    }

    /**
     * Método usado para realizar uma busca válida de um administrador por id.
     * @throws Exception Exceção lançada caso ocorra algum erro na busca.
     */
    @Test
    void buscarporId() throws Exception {
        Administrador esperado = new Administrador("Admteste", "administrador", 22222, 0);
        Administrador atual = DAO.getAdmDAO().buscarporId(0);
        assertEquals(esperado, atual);

        Administrador esperado_2 = new Administrador("Admteste2", "administrador", 11111, 1);
        Administrador atual_2 = DAO.getAdmDAO().buscarporId(1);
        assertEquals(esperado_2, atual_2);

    }

    /**
     * Método com um teste falho para a busca por id.
     * @throws AdmException Exceção lançada ao não encontrar nenhum administrador com o id buscado.
     */
    @Test
    void failbuscarporId() throws AdmException{
        try{
            Administrador admteste = new Administrador("Fabio", "Administrador", 98513256,10);
            DAO.getAdmDAO().buscarporId(10);
        }catch (AdmException e){
            assertEquals(AdmException.BUSCA_ID, e.getMessage());
        }

    }
}