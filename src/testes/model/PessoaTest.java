package model;

import com.example.pbl.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta classe contém os testes para a classe Pessoa.
 */
class PessoaTest {
    /**
     * Instância de Pessoa para testes.
     */
    private Pessoa pessoa1;

    /**
     * Segunda instância de Pessoa para testes.
     */
    private Pessoa pessoa2;

    /**
     * Método executado antes de cada teste para configurar as instâncias de Pessoa.
     */
    @BeforeEach
    public void setUp() {
        pessoa1 = new Pessoa("Ederson");
        pessoa2 = new Pessoa("Bruno teste", 1);
    }

    /**
     * Teste para verificar o método getNome().
     */
    @Test
    public void testGetNome() {
        assertEquals("Ederson", pessoa1.getNome());
        assertEquals("Bruno teste", pessoa2.getNome());
    }

    /**
     * Teste para verificar o método getNumIdentificacao().
     */
    @Test
    public void testGetNumIdentificacao() {
        assertEquals(-1, pessoa1.getNumIdentificacao());
        assertEquals(1, pessoa2.getNumIdentificacao());
    }

    /**
     * Teste para verificar o método setNome().
     */
    @Test
    public void testSetNome() {
        pessoa1.setNome("Paulo tst");
        assertEquals("Paulo tst", pessoa1.getNome());
    }

    /**
     * Teste para verificar o método setNumIdentificacao().
     */
    @Test
    public void testSetNumIdentificacao() {
        pessoa1.setNumIdentificacao(2);
        assertEquals(2, pessoa1.getNumIdentificacao());
    }

    /**
     * Teste para verificar o método equals().
     */
    @Test
    public void testEquals() {
        Pessoa pessoa3 = new Pessoa("Ederson");
        Pessoa pessoa4 = new Pessoa("Bruno teste", 1);

        assertTrue(pessoa1.equals(pessoa3));
        assertTrue(pessoa2.equals(pessoa4));
    }

    /**
     * Teste para verificar o método equals() quando os objetos não são iguais.
     */
    @Test
    public void testNotEquals() {
        Pessoa pessoa3 = new Pessoa("Paulo tst");
        Pessoa pessoa4 = new Pessoa("Bruno teste", 2);

        assertFalse(pessoa1.equals(pessoa3));
        assertFalse(pessoa2.equals(pessoa4));
    }
}
