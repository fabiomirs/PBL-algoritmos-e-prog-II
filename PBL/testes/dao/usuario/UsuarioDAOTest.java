package dao.usuario;

import dao.DAO;
import exceptions.UsuarioException;
import model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe criada para realizar os testes relacionados ao UsuarioDAOlist.
 */
class UsuarioDAOTest {
    /**
     * Representa o primeiro usuário de teste.
     */
    Usuario U1;
    /**
     * Representa o segundo usuário de teste.
     */
    Usuario U2;
    /**
     * Representa o terceiro usuário de teste.
     */
    Usuario U3;

    /**
     * Executa ANTES de cada teste.
     */
    @BeforeEach
    void setUp() {
        DAO.getUsuarioDAO().alteraParaPastaTeste();
        U1 = DAO.getUsuarioDAO().create(new Usuario("Hedley Griffin", "P.O. Box 571, 8659 Mauris, Rd.", 31495527));
        U2 = DAO.getUsuarioDAO().create(new Usuario("Gail Berry", "3489 Accumsan St.", 72664858));
        U3 = DAO.getUsuarioDAO().create(new Usuario("Gisela Dean", "Ap #124-2996 A Avenue", 53398795));
    }

    /**
     * Executa DEPOIS de cada teste.
     */
    @AfterEach
    void tearDown() {
        DAO.getUsuarioDAO().deleteMany();
        DAO.getUsuarioDAO().alteraParaPastaPrincipal();
    }

    /**
     * Realizando o teste para criação de um usuário, para isso compara-se um objeto já existente com o que será criado.
     */
    @Test
    void create() {
        Usuario esperado = new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777, 3, "Liberado");
        Usuario atual = DAO.getUsuarioDAO().create(new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777));

        assertEquals(esperado, atual);
    }

    /**
     * Método de teste usado para fazer a leitura e verificar se o tamanho da lista
     * de usuários corresponde a quantidade que é criada no setup().
     */
    @Test
    void read() {
        DAO.getUsuarioDAO().read();
        assertEquals(3, DAO.getUsuarioDAO().read().size());
    }

    /**
     * Testa o método update da classe UsuarioDAO.
     * @throws UsuarioException se ocorrer uma exceção relacionada ao usuário.
     */
    @Test
    void update() throws UsuarioException {
        U1.setNome("joao");
        U1.setEndereco("Rua dos testes");
        Usuario atual = DAO.getUsuarioDAO().update(U1);
        assertEquals(U1, atual);

        U2.setNome("carlos");
        U2.setNumIdentificacao(9);
        Usuario atual_2 = DAO.getUsuarioDAO().update(U2);
        assertEquals(U2, atual_2);

        U3.setNome("felipe");
        U3.setTelefone(99999999);
        U3.setEndereco("3489 Accumsan St.");
        Usuario atual_3 = DAO.getUsuarioDAO().update(U3);
        assertEquals(U3, atual_3);
    }

    /**
     * Testa o método update da classe UsuarioDAO com uma operação de falha.
     * @throws UsuarioException se ocorrer uma exceção relacionada ao usuário.
     */
    @Test
    void failUpdate() throws UsuarioException {
        try {
            Usuario userteste = new Usuario("Fabio", "Rua teste delete falho", 98513256);
            userteste.setNome("Fábio fail");
            DAO.getUsuarioDAO().update(userteste);
        } catch (UsuarioException e) {
            assertEquals(UsuarioException.UPDATE, e.getMessage());
        }
    }

    /**
     * Método de teste para realizar a exclusão de um usuário e garantir a veracidade
     * comparando a quantidade de elementos que existiam antes e depois da exclusão ser feita.
     * @throws UsuarioException se ocorrer uma exceção relacionada ao usuário.
     */
    @Test
    void delete() throws UsuarioException {
        int tamanho_esperado = DAO.getUsuarioDAO().read().size();
        DAO.getUsuarioDAO().delete(U1);
        assertEquals(tamanho_esperado - 1, DAO.getUsuarioDAO().read().size());
    }

    /**
     * Teste realizado tentando excluir um usuário inválido.
     * @throws UsuarioException se ocorrer uma exceção relacionada ao usuário.
     */
    @Test
    void failDelete() throws UsuarioException {
        try {
            DAO.getUsuarioDAO().delete(new Usuario("Fabio", "Rua teste delete falho", 98513256));
            fail("Uma exceção deveria ser gerada!!");
        } catch (UsuarioException e) {
            assertEquals(UsuarioException.DELETE, e.getMessage());
        }
    }

    /**
     * Teste para verificar se ao excluir todos os dados salvos o tamanho da lista de
     * usuários fica zerada.
     */
    @Test
    void deleteMany() {
        DAO.getUsuarioDAO().deleteMany();
        assertEquals(0, DAO.getUsuarioDAO().read().size());
    }

    /**
     * Testa o método buscarporId da classe UsuarioDAO.
     * @throws UsuarioException se ocorrer uma exceção relacionada ao usuário.
     */
    @Test
    void buscarporId() throws UsuarioException {
        Usuario esperado = new Usuario("Hedley Griffin", "P.O. Box 571, 8659 Mauris, Rd.", 31495527, 0, "Liberado");
        Usuario atual = DAO.getUsuarioDAO().buscarporId(0);
        assertEquals(esperado, atual);

        Usuario esperado_2 = new Usuario("Gisela Dean", "Ap #124-2996 A Avenue", 53398795, 2, "Liberado");
        Usuario atual_2 = DAO.getUsuarioDAO().buscarporId(2);
        assertEquals(esperado_2, atual_2);
    }

    /**
     * Método com um teste falho para a busca por id.
     * @throws UsuarioException se ocorrer uma exceção relacionada ao usuário.
     */
    @Test
    void failbuscarporId() throws UsuarioException {
        try {
            Usuario esperado = new Usuario("Hedley fail", "Fail P.O. Box 571, 8659 Mauris, Rd.", 314527, 10, "Liberado");
            DAO.getUsuarioDAO().buscarporId(10);
        } catch (UsuarioException e) {
            assertEquals(UsuarioException.BUSCA_ID, e.getMessage());
        }
    }
}
