package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecarioTest {
    private Bibliotecario bibliotecario1;
    private Bibliotecario bibliotecario2;

    @BeforeEach
    public void setUp() {
        bibliotecario1 = new Bibliotecario("Ederson", "Bibliotecario", 123456);
        bibliotecario2 = new Bibliotecario("Fábio", "Bibliotecario", 789012, 1);
    }

    @Test
    public void testGetCargo() {
        assertEquals("Bibliotecario", bibliotecario1.getCargo());
        assertEquals("Bibliotecario", bibliotecario2.getCargo());
    }

    @Test
    public void testSetCargo() {
        bibliotecario1.setCargo("Bibliotecario");
        assertEquals("Bibliotecario", bibliotecario1.getCargo());
    }

    @Test
    public void testGetSenhaAcesso() {
        assertEquals(123456, bibliotecario1.getSenhaAcesso());
        assertEquals(789012, bibliotecario2.getSenhaAcesso());
    }

    @Test
    public void testSetSenhaAcesso() {
        bibliotecario1.setSenhaAcesso(987654);
        assertEquals(987654, bibliotecario1.getSenhaAcesso());
    }

    @Test
    public void testEquals() {
        Bibliotecario bibliotecario3 = new Bibliotecario("Ederson", "Bibliotecario", 123456);
        Bibliotecario bibliotecario4 = new Bibliotecario("Fábio", "Bibliotecario", 789012, 1);

        assertTrue(bibliotecario1.equals(bibliotecario3));
        assertTrue(bibliotecario2.equals(bibliotecario4));
    }

    @Test
    public void testNotEquals() {
        Bibliotecario bibliotecario3 = new Bibliotecario("Bruno", "Bibliotecario", 123456);
        Bibliotecario bibliotecario4 = new Bibliotecario("Fábio", "Bibliotecario", 987654, 2);

        assertFalse(bibliotecario1.equals(bibliotecario3));
        assertFalse(bibliotecario2.equals(bibliotecario4));
    }

}