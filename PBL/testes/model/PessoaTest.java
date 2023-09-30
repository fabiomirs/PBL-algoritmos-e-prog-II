package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {
    private Pessoa pessoa1;
    private Pessoa pessoa2;

    @BeforeEach
    public void setUp() {
        pessoa1 = new Pessoa("Ederson");
        pessoa2 = new Pessoa("Bruno teste", 1);
    }

    @Test
    public void testGetNome() {
        assertEquals("Ederson", pessoa1.getNome());
        assertEquals("Bruno teste", pessoa2.getNome());
    }

    @Test
    public void testGetNumIdentificacao() {
        assertEquals(-1, pessoa1.getNumIdentificacao());
        assertEquals(1, pessoa2.getNumIdentificacao());
    }

    @Test
    public void testSetNome() {
        pessoa1.setNome("Paulo tst");
        assertEquals("Paulo tst", pessoa1.getNome());
    }

    @Test
    public void testSetNumIdentificacao() {
        pessoa1.setNumIdentificacao(2);
        assertEquals(2, pessoa1.getNumIdentificacao());
    }

    @Test
    public void testEquals() {
        Pessoa pessoa3 = new Pessoa("Ederson");
        Pessoa pessoa4 = new Pessoa("Bruno teste", 1);

        assertTrue(pessoa1.equals(pessoa3));
        assertTrue(pessoa2.equals(pessoa4));
    }

    @Test
    public void testNotEquals() {
        Pessoa pessoa3 = new Pessoa("Paulo tst");
        Pessoa pessoa4 = new Pessoa("Bruno teste", 2);

        assertFalse(pessoa1.equals(pessoa3));
        assertFalse(pessoa2.equals(pessoa4));
    }

}