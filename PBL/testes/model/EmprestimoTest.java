package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta classe contém os testes para a classe Emprestimo.
 */
class EmprestimoTest {
    /**
     * Instância de Livro para testes.
     */
    private Livro livro;

    /**
     * Instância de Usuário para testes.
     */
    private Usuario usuario;

    /**
     * Instância de Emprestimo para testes.
     */
    private Emprestimo emprestimo1;

    /**
     * Segunda instância de Emprestimo para testes.
     */
    private Emprestimo emprestimo2;

    /**
     * Método executado antes de cada teste para configurar as instâncias.
     */
    @BeforeEach
    public void setUp() {
        livro = new Livro("Livro teste", "Editora A", 123456, "Local A", "Autor A", "2022", "Educação");
        usuario = new Usuario("Alice", "123 Main St", 555123456, 1, "Liberado");

        emprestimo1 = new Emprestimo(livro, usuario);
        emprestimo2 = new Emprestimo(livro, usuario, 2);
    }

    /**
     * Teste para verificar o método getStatus().
     */
    @Test
    public void testGetStatus() {
        assertEquals("Fechado", emprestimo1.getStatus());
        assertEquals("Fechado", emprestimo2.getStatus());
    }

    /**
     * Teste para verificar o método setStatus().
     */
    @Test
    public void testSetStatus() {
        emprestimo1.setStatus("Aberto");
        assertEquals("Aberto", emprestimo1.getStatus());
    }

    /**
     * Teste para verificar o método setDataDevolucao().
     */
    @Test
    public void testSetDataDevolucao() {
        LocalDate novaData = LocalDate.now().plusDays(14);
        emprestimo1.setDataDevolucao(novaData);
        assertEquals(novaData, emprestimo1.getDataDevolucao());
    }

    /**
     * Teste para verificar o método getDataEmprestimo().
     */
    @Test
    public void testGetDataEmprestimo() {
        LocalDate dataEmprestimo = LocalDate.now();
        assertEquals(dataEmprestimo, emprestimo1.getDataEmprestimo());
    }

    /**
     * Teste para verificar o método getDataDevolucao().
     */
    @Test
    public void testGetDataDevolucao() {
        LocalDate dataDevolucao = LocalDate.now().plusDays(7);
        assertEquals(dataDevolucao, emprestimo1.getDataDevolucao());
    }

    /**
     * Teste para verificar o método getId().
     */
    @Test
    public void testGetId() {
        assertEquals(1, emprestimo1.getId());
        assertEquals(2, emprestimo2.getId());
    }

    /**
     * Teste para verificar o método getLivro().
     */
    @Test
    public void testGetLivro() {
        assertEquals(livro, emprestimo1.getLivro());
        assertEquals(livro, emprestimo2.getLivro());
    }

    /**
     * Teste para verificar o método getUsuario().
     */
    @Test
    public void testGetUsuario() {
        assertEquals(usuario, emprestimo1.getUsuario());
        assertEquals(usuario, emprestimo2.getUsuario());
    }

    /**
     * Teste para verificar o método equals().
     */
    @Test
    public void testEquals() {
        Emprestimo emprestimo3 = new Emprestimo(livro, usuario);
        Emprestimo emprestimo4 = new Emprestimo(livro, usuario, 2);

        assertTrue(emprestimo1.equals(emprestimo3));
        assertTrue(emprestimo2.equals(emprestimo4));
    }

    /**
     * Teste para verificar o método equals() quando os objetos não são iguais.
     */
    @Test
    public void testNotEquals() {
        Livro outroLivro = new Livro("Outro Livro", "Editora C", 987654, "Local C", "Autor C", "2023", "Esporte");
        Usuario outroUsuario = new Usuario("Fábio", "456 Elm St", 555789012, 3, "Liberado");
        Emprestimo emprestimo3 = new Emprestimo(outroLivro, outroUsuario);
        Emprestimo emprestimo4 = new Emprestimo(livro, usuario, 3);

        assertFalse(emprestimo1.equals(emprestimo3));
        assertFalse(emprestimo2.equals(emprestimo4));
    }
}
