package dao.administrador;

import dao.DAO;
import exceptions.AdmException;

import model.Administrador;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdmDAOlistTest {

    Administrador Admteste;
    Administrador Admteste2;
    Administrador Admteste3;

    /**
     * Executa ANTES de cada teste
     */
    @BeforeEach
    void setUp() {
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
    }

    @Test
    void create() {
        Administrador esperado = new Administrador("FabioAdm", "administrador", 7777, 3);
        Administrador atual = DAO.getAdmDAO().create(new Administrador("FabioAdm", "administrador", 7777));

        assertEquals(esperado, atual);
    }

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

    @Test
    void delete() throws AdmException {
        int tamanho_esperado = DAO.getAdmDAO().read().size();
        DAO.getAdmDAO().delete(Admteste);
        assertEquals(tamanho_esperado-1, DAO.getAdmDAO().read().size());
    }

    @Test
    void failDelete(){
        try {
            DAO.getAdmDAO().delete(new Administrador("Fábio", "administrador", 00000));
            fail("Uma exceção deveria ser gerada!!");
        } catch (AdmException e) {
            assertEquals(AdmException.DELETE, e.getMessage());
        }

    }

    @Test
    void read() {
        DAO.getAdmDAO().read();
        assertEquals(3, DAO.getAdmDAO().read().size());
    }

    @Test
    void deleteMany() {
        DAO.getAdmDAO().deleteMany();
        assertEquals(0, DAO.getAdmDAO().read().size());
    }

    @Test
    void buscarporId() throws Exception {
        Administrador esperado = new Administrador("Admteste", "administrador", 22222, 0);
        Administrador atual = DAO.getAdmDAO().buscarporId(0);
        assertEquals(esperado, atual);

        Administrador esperado_2 = new Administrador("Admteste2", "administrador", 11111, 1);
        Administrador atual_2 = DAO.getAdmDAO().buscarporId(1);
        assertEquals(esperado_2, atual_2);

    }

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