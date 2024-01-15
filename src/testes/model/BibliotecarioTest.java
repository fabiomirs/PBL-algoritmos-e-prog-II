package model;

import com.example.pbl.model.Bibliotecario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta classe contém os testes para a classe Bibliotecário.
 */
class BibliotecarioTest {
    /**
     * Representa o primeiro bibliotecario para testes.
     */
    private Bibliotecario bibliotecario1;

    /**
     * Representa o segundo bibliotecario para testes.
     */
    private Bibliotecario bibliotecario2;

    /**
     * Método executado antes de cada teste para configurar as instâncias de Bibliotecário.
     */
    @BeforeEach
    public void setUp() {
        bibliotecario1 = new Bibliotecario("Ederson", "Bibliotecario", 123456);
        bibliotecario2 = new Bibliotecario("Fábio", "Bibliotecario", 789012, 1);
    }

    /**
     * Teste para verificar o método getCargo().
     */
    @Test
    public void testGetCargo() {
        assertEquals("Bibliotecario", bibliotecario1.getCargo());
        assertEquals("Bibliotecario", bibliotecario2.getCargo());
    }

    /**
     * Teste para verificar o método setCargo().
     */
    @Test
    public void testSetCargo() {
        bibliotecario1.setCargo("Bibliotecario");
        assertEquals("Bibliotecario", bibliotecario1.getCargo());
    }

    /**
     * Teste para verificar o método getSenhaAcesso().
     */
    @Test
    public void testGetSenhaAcesso() {
        assertEquals(123456, bibliotecario1.getSenhaAcesso());
        assertEquals(789012, bibliotecario2.getSenhaAcesso());
    }

    /**
     * Teste para verificar o método setSenhaAcesso().
     */
    @Test
    public void testSetSenhaAcesso() {
        bibliotecario1.setSenhaAcesso(987654);
        assertEquals(987654, bibliotecario1.getSenhaAcesso());
    }

    /**
     * Teste para verificar o método equals().
     */
    @Test
    public void testEquals() {
        Bibliotecario bibliotecario3 = new Bibliotecario("Ederson", "Bibliotecario", 123456);
        Bibliotecario bibliotecario4 = new Bibliotecario("Fábio", "Bibliotecario", 789012, 1);

        assertTrue(bibliotecario1.equals(bibliotecario3));
        assertTrue(bibliotecario2.equals(bibliotecario4));
    }

    /**
     * Teste para verificar o método equals() quando os objetos não são iguais.
     */
    @Test
    public void testNotEquals() {
        Bibliotecario bibliotecario3 = new Bibliotecario("Bruno", "Bibliotecario", 123456);
        Bibliotecario bibliotecario4 = new Bibliotecario("Fábio", "Bibliotecario", 987654, 2);

        assertFalse(bibliotecario1.equals(bibliotecario3));
        assertFalse(bibliotecario2.equals(bibliotecario4));
    }
}
