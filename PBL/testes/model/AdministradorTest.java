package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {
    private Administrador administrador1;
    private Administrador administrador2;

    @BeforeEach
    public void setUp() {
        administrador1 = new Administrador("Ederson", "Administrador", 123456);
        administrador2 = new Administrador("Fábio", "Administrador", 789012, 1);
    }

    @Test
    public void testGetCargo() {
        assertEquals("Administrador", administrador1.getCargo());
        assertEquals("Administrador", administrador2.getCargo());
    }

    @Test
    public void testSetCargo() {
        administrador1.setCargo("Bibliotecario");
        assertEquals("Bibliotecario", administrador1.getCargo());
    }

    @Test
    public void testGetSenhaAcesso() {
        assertEquals(123456, administrador1.getSenhaAcesso());
        assertEquals(789012, administrador2.getSenhaAcesso());
    }

    @Test
    public void testSetSenhaAcesso() {
        administrador1.setSenhaAcesso(654);
        assertEquals(654, administrador1.getSenhaAcesso());
    }

}