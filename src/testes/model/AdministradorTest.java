package model;

import com.example.pbl.model.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta classe contém os testes para a classe Administrador.
 */
class AdministradorTest {
    /**
     * Representa o primeiro Administrador para testes.
     */
    private Administrador administrador1;

    /**
     * Representa o segundo Administrador para testes.
     */
    private Administrador administrador2;

    /**
     * Método executado antes de cada teste para configurar as instâncias de Administrador.
     */
    @BeforeEach
    public void setUp() {
        administrador1 = new Administrador("Ederson", "Administrador", 123456);
        administrador2 = new Administrador("Fábio", "Administrador", 789012, 1);
    }

    /**
     * Teste para verificar o método getCargo().
     */
    @Test
    public void testGetCargo() {
        assertEquals("Administrador", administrador1.getCargo());
        assertEquals("Administrador", administrador2.getCargo());
    }

    /**
     * Teste para verificar o método setCargo().
     */
    @Test
    public void testSetCargo() {
        administrador1.setCargo("Bibliotecario");
        assertEquals("Bibliotecario", administrador1.getCargo());
    }

    /**
     * Teste para verificar o método getSenhaAcesso().
     */
    @Test
    public void testGetSenhaAcesso() {
        assertEquals(123456, administrador1.getSenhaAcesso());
        assertEquals(789012, administrador2.getSenhaAcesso());
    }

    /**
     * Teste para verificar o método setSenhaAcesso().
     */
    @Test
    public void testSetSenhaAcesso() {
        administrador1.setSenhaAcesso(654);
        assertEquals(654, administrador1.getSenhaAcesso());
    }
}
