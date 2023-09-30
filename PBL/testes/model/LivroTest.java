package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {
    private Livro livro1;
    private Livro livro2;

    @BeforeEach
    public void setUp() {
        livro1 = new Livro("Livro 1", "Editora A", 123456, "Local A", "Autor A", "2022", "Ficção");
        livro2 = new Livro("livro viajante", "Editora B", 789012, "Local B", "Autor B", "2021", "Não Ficção");
    }

    @Test
    public void testGetTitulo() {
        assertEquals("Livro 1", livro1.getTitulo());
        assertEquals("livro viajante", livro2.getTitulo());
    }

    @Test
    public void testSetTitulo() {
        livro1.setTitulo("Novo Livro");
        assertEquals("Novo Livro", livro1.getTitulo());
    }

    @Test
    public void testGetEditora() {
        assertEquals("Editora A", livro1.getEditora());
        assertEquals("Editora B", livro2.getEditora());
    }

    @Test
    public void testSetEditora() {
        livro1.setEditora("Nova Editora");
        assertEquals("Nova Editora", livro1.getEditora());
    }

    @Test
    public void testGetCodigoIsbn() {
        assertEquals(123456, livro1.getCodigoIsbn());
        assertEquals(789012, livro2.getCodigoIsbn());
    }

    @Test
    public void testSetCodigoIsbn() {
        livro1.setCodigoIsbn(987654);
        assertEquals(987654, livro1.getCodigoIsbn());
    }

    @Test
    public void testGetLocalizacao() {
        assertEquals("Local A", livro1.getLocalizacao());
        assertEquals("Local B", livro2.getLocalizacao());
    }

    @Test
    public void testSetLocalizacao() {
        livro1.setLocalizacao("Nova Localização");
        assertEquals("Nova Localização", livro1.getLocalizacao());
    }

    @Test
    public void testGetStatusLivro() {
        assertEquals("Disponivel", livro1.getStatusLivro());
        assertEquals("Disponivel", livro2.getStatusLivro());
    }

    @Test
    public void testSetStatusLivro() {
        livro1.setStatusLivro("Emprestado");
        assertEquals("Emprestado", livro1.getStatusLivro());
    }

    @Test
    public void testGetAutor() {
        assertEquals("Autor A", livro1.getAutor());
        assertEquals("Autor B", livro2.getAutor());
    }

    @Test
    public void testSetAutor() {
        livro1.setAutor("Novo Autor");
        assertEquals("Novo Autor", livro1.getAutor());
    }

    @Test
    public void testGetAnoPublicacao() {
        assertEquals("2022", livro1.getAnoPublicacao());
        assertEquals("2021", livro2.getAnoPublicacao());
    }

    @Test
    public void testSetAnoPublicacao() {
        livro1.setAnoPublicacao("2023");
        assertEquals("2023", livro1.getAnoPublicacao());
    }

    @Test
    public void testGetCategoria() {
        assertEquals("Ficção", livro1.getCategoria());
        assertEquals("Não Ficção", livro2.getCategoria());
    }

    @Test
    public void testSetCategoria() {
        livro1.setCategoria("Romance");
        assertEquals("Romance", livro1.getCategoria());
    }

    @Test
    public void testGetId() {
        assertEquals(0, livro1.getId());
        assertNotEquals(1, livro2.getId());
    }

    @Test
    public void testSetId() {
        livro1.setId(2);
        assertEquals(2, livro1.getId());
    }

    @Test
    public void testGetProximoId() {
        assertEquals(0, livro1.getProximoId());
        assertNotEquals(1, livro2.getProximoId());
    }

    @Test
    public void testSetProximoId() {
        livro1.setProximoId(2);
        assertEquals(2, livro1.getProximoId());
    }

    @Test
    public void testGetReservas() {
        assertNotNull(livro1.getReservas());
        assertNotNull(livro2.getReservas());
        assertTrue(livro1.getReservas().isEmpty());
        assertTrue(livro2.getReservas().isEmpty());
    }

    @Test
    public void testEquals() {
        Livro livro3 = new Livro("Livro 1", "Editora A", 123456, "Local A", "Autor A", "2022", "Ficção");
        Livro livro4 = new Livro("livro viajante", "Editora B", 789012, "Local B", "Autor B", "2021", "Não Ficção");

        assertTrue(livro1.equals(livro3));
        assertTrue(livro2.equals(livro4));
    }

    @Test
    public void testNotEquals() {
        Livro livro3 = new Livro("Outro Livro", "Editora C", 987654, "Local C", "Autor C", "2023", "Romance");
        Livro livro4 = new Livro("Livro 2", "Editora B", 789012, "Local B", "Autor B", "2021", "Não Ficção", 2);

        assertFalse(livro1.equals(livro3));
        assertFalse(livro2.equals(livro4));
    }

}