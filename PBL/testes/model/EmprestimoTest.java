package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {
    private Livro livro;
    private Usuario usuario;
    private Emprestimo emprestimo1;
    private Emprestimo emprestimo2;

    @BeforeEach
    public void setUp() {

        livro = new Livro("Livro teste", "Editora A", 123456, "Local A", "Autor A", "2022", "Educação");
        usuario = new Usuario("Alice", "123 Main St", 555123456, 1, "Liberado");

        emprestimo1 = new Emprestimo(livro, usuario);
        emprestimo2 = new Emprestimo(livro, usuario, 2);
    }

    @Test
    public void testGetStatus() {
        assertEquals("Fechado", emprestimo1.getStatus());
        assertEquals("Fechado", emprestimo2.getStatus());
    }

    @Test
    public void testSetStatus() {
        emprestimo1.setStatus("Aberto");
        assertEquals("Aberto", emprestimo1.getStatus());
    }

    @Test
    public void testSetDataDevolucao() {
        LocalDate novaData = LocalDate.now().plusDays(14);
        emprestimo1.setDataDevolucao(novaData);
        assertEquals(novaData, emprestimo1.getDataDevolucao());
    }

    @Test
    public void testGetDataEmprestimo() {
        LocalDate dataEmprestimo = LocalDate.now();
        assertEquals(dataEmprestimo, emprestimo1.getDataEmprestimo());
    }

    @Test
    public void testGetDataDevolucao() {
        LocalDate dataDevolucao = LocalDate.now().plusDays(7);
        assertEquals(dataDevolucao, emprestimo1.getDataDevolucao());
    }

    @Test
    public void testGetId() {
        assertEquals(1, emprestimo1.getId());
        assertEquals(2, emprestimo2.getId());
    }

    @Test
    public void testGetLivro() {
        assertEquals(livro, emprestimo1.getLivro());
        assertEquals(livro, emprestimo2.getLivro());
    }

    @Test
    public void testGetUsuario() {
        assertEquals(usuario, emprestimo1.getUsuario());
        assertEquals(usuario, emprestimo2.getUsuario());
    }

    @Test
    public void testEquals() {
        Emprestimo emprestimo3 = new Emprestimo(livro, usuario);
        Emprestimo emprestimo4 = new Emprestimo(livro, usuario, 2);

        assertTrue(emprestimo1.equals(emprestimo3));
        assertTrue(emprestimo2.equals(emprestimo4));
    }

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