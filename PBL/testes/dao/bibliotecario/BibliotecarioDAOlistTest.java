package dao.bibliotecario;

import dao.DAO;

import exceptions.BibliotecarioExcpetion;

import model.Bibliotecario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecarioDAOlistTest {

    Bibliotecario B1;
    Bibliotecario B2;
    Bibliotecario B3;


    /**
     * Executa ANTES de cada teste
     */
    @BeforeEach
    void setUp() {
        B1 = DAO.getBibliotecarioDAO().create(new Bibliotecario("Dawn Pittman", "Bibliotecario", 15377));
        B2 = DAO.getBibliotecarioDAO().create(new Bibliotecario("Berk Bennett", "Bibliotecario", 52964));
        B3 = DAO.getBibliotecarioDAO().create(new Bibliotecario("Upton Santiago", "Bibliotecario", 58634));
    }

    /**
     * Executa DEPOIS de cada teste
     */
    @AfterEach
    void tearDown() {
        DAO.getBibliotecarioDAO().deleteMany();
    }

    @Test
    void create() {
        Bibliotecario esperado = new Bibliotecario("FabioBli", "Bibliotecario", 7777, 3);
        Bibliotecario atual = DAO.getBibliotecarioDAO().create(new Bibliotecario("FabioBli", "Bibliotecario", 7777));

        assertEquals(esperado, atual);
    }

    @Test
    void read() {
        DAO.getBibliotecarioDAO().read();
        assertEquals(3, DAO.getBibliotecarioDAO().read().size());
    }

    @Test
    void update() throws BibliotecarioExcpetion{
        B2.setNome("joao");
        B2.setSenhaAcesso(99999);
        Bibliotecario atual = DAO.getBibliotecarioDAO().update(B2);
        assertEquals(B2, atual);


        B1.setNome("carlos");
        B1.setNumIdentificacao(9);
        Bibliotecario atual_2 = DAO.getBibliotecarioDAO().update(B1);
        assertEquals(B1, atual_2);

        B3.setNome("felipe");
        B3.setCargo("Bibliotecário");
        B3.setSenhaAcesso(45598);
        Bibliotecario atual_3 = DAO.getBibliotecarioDAO().update(B3);
        assertEquals(B3, atual_3);
    }

    @Test
    void failUpdate() throws BibliotecarioExcpetion {
        try {
            Bibliotecario biblioteste = new Bibliotecario("Fabio", "Bibliotecario", 98513256);
            biblioteste.setNome("Fábio fail");
            biblioteste.setNumIdentificacao(99);
            biblioteste.setSenhaAcesso(7895654);
            DAO.getBibliotecarioDAO().update(biblioteste);
        }catch (BibliotecarioExcpetion e) {
            assertEquals(BibliotecarioExcpetion.UPDATE, e.getMessage());
        }
    }

    @Test
    void delete() throws BibliotecarioExcpetion {
        int tamanho_esperado = DAO.getBibliotecarioDAO().read().size();
        DAO.getBibliotecarioDAO().delete(B1);
        assertEquals(tamanho_esperado-1, DAO.getBibliotecarioDAO().read().size());
    }

    @Test
    void failDelete() throws BibliotecarioExcpetion{
        try {
            DAO.getBibliotecarioDAO().delete(new Bibliotecario("Fábio", "Bibliotecario", 00000));
            fail("Uma exceção deveria ser gerada!!");
        } catch (BibliotecarioExcpetion e) {
            assertEquals(BibliotecarioExcpetion.DELETE, e.getMessage());
        }

    }

    @Test
    void deleteMany() {
        DAO.getBibliotecarioDAO().deleteMany();
        assertEquals(0, DAO.getBibliotecarioDAO().read().size());

    }

    @Test
    void buscarporId() throws BibliotecarioExcpetion {

        Bibliotecario esperado = new Bibliotecario("Dawn Pittman", "Bibliotecario", 15377, 0);
        Bibliotecario atual = DAO.getBibliotecarioDAO().buscarporId(0);
        assertEquals(esperado, atual);

        Bibliotecario esperado_2 = new Bibliotecario("Upton Santiago", "Bibliotecario", 58634, 2);
        Bibliotecario atual_2 = DAO.getBibliotecarioDAO().buscarporId(2);
        assertEquals(esperado_2, atual_2);
    }

    @Test
    void failbuscarporId() throws BibliotecarioExcpetion{
        try{
            Bibliotecario biblioteste = new Bibliotecario("Fabio", "Bibliotecario", 98513256, 10);
            DAO.getBibliotecarioDAO().buscarporId(10);
        }catch (BibliotecarioExcpetion e){
            assertEquals(BibliotecarioExcpetion.BUSCA_ID, e.getMessage());
        }

    }
}