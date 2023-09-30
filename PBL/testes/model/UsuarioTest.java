package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    public void setUp() {
        usuario1 = new Usuario("Ederson", "Rua 123", 555123456, 1, "Liberado");
        usuario2 = new Usuario("Felipe", "Rua 456", 555789012);
    }

    @Test
    public void testGetEndereco() {
        assertEquals("Rua 123", usuario1.getEndereco());
        assertEquals("Rua 456", usuario2.getEndereco());
    }

    @Test
    public void testSetEndereco() {
        usuario1.setEndereco("Rua teste");
        assertEquals("Rua teste", usuario1.getEndereco());
    }

    @Test
    public void testGetTelefone() {
        assertEquals(555123456, usuario1.getTelefone());
        assertEquals(555789012, usuario2.getTelefone());
    }

    @Test
    public void testSetTelefone() {
        usuario1.setTelefone(555987654);
        assertEquals(555987654, usuario1.getTelefone());
    }

    @Test
    public void testGetStatusConta() {
        assertEquals("Liberado", usuario1.getStatusConta());
        assertNotNull(usuario2.getStatusConta());
    }

    @Test
    public void testSetStatusConta() {
        usuario2.setStatusConta("Bloqueado");
        assertEquals("Bloqueado", usuario2.getStatusConta());
    }

    @Test
    public void testGetLimRenovacao() {
        assertEquals(2, usuario1.getLimRenovacao());
        assertEquals(2, usuario2.getLimRenovacao());
    }

    @Test
    public void testSetLimRenovacao() {
        usuario1.setlimRenovacao(3);
        assertEquals(3, usuario1.getLimRenovacao());
    }

    @Test
    public void testEquals() {
        Usuario usuario3 = new Usuario("Ederson", "Rua 123", 555123456, 1, "Liberado");
        Usuario usuario4 = new Usuario("Felipe", "Rua 456", 555789012);

        assertTrue(usuario1.equals(usuario3));
        assertTrue(usuario2.equals(usuario4));
    }

    @Test
    public void testNotEquals() {
        Usuario usuario3 = new Usuario("Fábio", "Rua 123", 555123456, 2, "Bloqueado");
        Usuario usuario4 = new Usuario("Felipe", "Rua teste", 555987654);

        assertFalse(usuario1.equals(usuario3));
        assertFalse(usuario2.equals(usuario4));
    }

}