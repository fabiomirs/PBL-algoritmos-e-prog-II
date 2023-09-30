package dao.livro;

import dao.DAO;
import exceptions.LivroException;
import model.Livro;
import model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivroDAOlistTest {
    Livro livro1;
    Livro livro2;
    Livro livro3;

    /**
     * Executa ANTES de cada teste
     */
    @BeforeEach
    void setUp() {
         livro1 = DAO.getLivroDAO().create(new Livro("x", "Penguin Random House", 1, "setor e", "joao", "Nov 16, 2022", "teste"));
         livro2 = DAO.getLivroDAO().create(new Livro("b", "HarperCollins", 2, "setor d", "joao", "Mar 2, 2024", "Ficção"));
         livro3 = DAO.getLivroDAO().create(new Livro("c", "Macmillan Publishers", 3, "setor c", "joao", "Nov 5, 2022", "educacao"));


    }

    /**
     * Executa DEPOIS de cada teste
     */
    @AfterEach
    void tearDown() {
        DAO.getLivroDAO().deleteMany();
    }

    @Test
    void create() {
        //primeiro teste
        Livro novoLivro = new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao", 3);
        Livro livroCriado = DAO.getLivroDAO().create(new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao"));

        assertNotNull(livroCriado);
        assertEquals(novoLivro, livroCriado);

        //segundo teste
        Livro novoLivro2 = new Livro("Livro Novo 2", "Editora Nova", 17, "setor b", "maria", "Dec 2, 2023", "Aventura", 4);
        Livro livroCriado2 = DAO.getLivroDAO().create(new Livro("Livro Novo 2", "Editora Nova", 17, "setor b", "maria", "Dec 2, 2023", "Aventura"));

        assertNotNull(livroCriado2);
        assertEquals(novoLivro2, livroCriado2);

        // Verifica se o livro foi realmente adicionado à lista
        assertTrue(DAO.getLivroDAO().read().contains(novoLivro));
    }

    @Test
    void read() {
        DAO.getLivroDAO().read();
        assertEquals(3, DAO.getLivroDAO().read().size());
    }

    @Test
    void update()throws LivroException {

        //primeiro teste
        livro1.setTitulo("Novo Título");
        livro1.setAutor("Novo Autor");
        Livro livroAtualizado = DAO.getLivroDAO().update(livro1);

        assertNotNull(livroAtualizado);
        assertEquals("Novo Título", livroAtualizado.getTitulo());
        assertEquals("Novo Autor", livroAtualizado.getAutor());


        //segundo teste
        livro3.setCategoria("Ficção Científica");
        Livro livroAtt2 = DAO.getLivroDAO().update(livro3);

        assertNotNull(livroAtt2);
        assertEquals("Ficção Científica", livroAtt2.getCategoria());

        // Verifica se o livro atualizado está presente na lista
        assertTrue(DAO.getLivroDAO().read().contains(livroAtt2));
    }

    @Test
    void failUpdate() throws LivroException {
        try {
            Livro livrotst = new Livro("Livro teste falho", "Editora falha", 47, "setor j", "andre", "Dec 1, 2023", "educacao", 3);
            livrotst.setAutor("Fábio fail");
            livrotst.setCategoria("esporte");
            livrotst.setEditora("fail");
            DAO.getLivroDAO().update(livrotst);
        }catch (LivroException e) {
            assertEquals(LivroException.UPDATE, e.getMessage());
        }
    }

    @Test
    void delete() throws LivroException {
        //primeiro teste
        int tamanho_esperado = DAO.getLivroDAO().read().size();
        DAO.getLivroDAO().delete(livro1);
        assertEquals(tamanho_esperado-1, DAO.getLivroDAO().read().size());


        //segundo teste
        DAO.getLivroDAO().delete(livro3);
        assertEquals(tamanho_esperado - 2, DAO.getLivroDAO().read().size());

        //verifica se o livro esta na lista
        assertFalse(DAO.getLivroDAO().read().contains(livro3));

    }

    @Test
    void failDelete() {
        try {
            DAO.getLivroDAO().delete(new Livro("z", "Schuster", 99, "setor z", "andre", "Jan 30, 2023", "Romance"));
            fail("Uma exceção deveria ser gerada!!");
        } catch (LivroException e) {
            assertEquals(LivroException.DELETE, e.getMessage());
        }

    }

    @Test
    void deleteMany() {
        DAO.getLivroDAO().deleteMany();
        assertEquals(0, DAO.getLivroDAO().read().size());

    }

    @Test
    void buscarporId() throws LivroException {
        Livro esperado = livro1;
        Livro atual = DAO.getLivroDAO().buscarporId(esperado.getId());
        assertEquals(esperado, atual);

        Livro esperado_2 = livro3;
        Livro atual_2 = DAO.getLivroDAO().buscarporId(esperado_2.getId());
        assertEquals(esperado_2, atual_2);
    }

    @Test
    void failbuscarporId() throws LivroException{
        try{
            Livro livrotst = new Livro("Livro teste falho", "Editora falha", 47, "setor j", "andre", "Dec 1, 2023", "educacao", 10);
            DAO.getLivroDAO().buscarporId(10);
        }catch (LivroException e){
            assertEquals(LivroException.BUSCA_ID, e.getMessage());
        }

    }

    @Test
    void buscarporTitulo() throws LivroException{
        String titulo = "x";
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarporTitulo(titulo);

        assertNotNull(livrosEncontrados);
        assertEquals(1, livrosEncontrados.size());
        assertEquals(titulo, livrosEncontrados.get(0).getTitulo());
    }

    @Test
    void failbuscarporTitulo() throws LivroException{
        try{
            String titulo = "Titulo teste falho";
            DAO.getLivroDAO().buscarporTitulo(titulo);
        }catch (LivroException e){
            assertEquals(LivroException.BUSCA_TITULO, e.getMessage());
        }

    }

    @Test
    void buscarporAutor() throws LivroException {
        String autor = "joao";
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarporAutor(autor);

        assertNotNull(livrosEncontrados);
        assertEquals(3, livrosEncontrados.size());
        assertTrue(livrosEncontrados.stream().allMatch(l -> l.getAutor().equals(autor)));
    }

    @Test
    void failbuscarporAutor() throws LivroException{
        try{
            String autor = "Autor teste falho";
            DAO.getLivroDAO().buscarporAutor(autor);
        }catch (LivroException e){
            assertEquals(LivroException.BUSCA_AUTOR, e.getMessage());
        }

    }

    @Test
    void buscarPorIsbn() throws LivroException{
        int isbn = 2;
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarPorIsbn(isbn);

        assertNotNull(livrosEncontrados);
        assertEquals(1, livrosEncontrados.size());
        assertEquals(isbn, livrosEncontrados.get(0).getCodigoIsbn());
    }

    @Test
    void failbuscarPorIsbn() throws LivroException{
        try{
            int codigo = 7864;
            DAO.getLivroDAO().buscarPorIsbn(codigo);
        }catch (LivroException e){
            assertEquals(LivroException.BUSCA_ISBN, e.getMessage());
        }

    }

    @Test
    void buscarPorCategoria() throws LivroException{
        String categoria = "teste";
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarPorCategoria(categoria);
        assertNotNull(livrosEncontrados);
        assertEquals(1, livrosEncontrados.size());
        assertTrue(livrosEncontrados.stream().allMatch(l -> l.getCategoria().equals(categoria)));
    }

    @Test
    void failbuscarPorCategoria() throws LivroException{
        try{
            String categoria = "categoria falha";
            DAO.getLivroDAO().buscarPorCategoria(categoria);
        }catch (LivroException e){
            assertEquals(LivroException.BUSCA_CATEGORIA, e.getMessage());
        }

    }

    @Test
    void LivrosComReserva() throws LivroException {
        Usuario usuariotest = new Usuario("nome teste","Rua teste", 7534682,100, "Liberado");
        DAO.getLivroDAO().realizarReserva(usuariotest, livro3.getId());
        assertEquals(1, DAO.getLivroDAO().livrosComReserva().size());
    }

    @Test
    void failLivrosComReserva() throws LivroException{
        try{
            DAO.getLivroDAO().livrosComReserva();
        }catch (LivroException e){
            assertEquals(LivroException.QTD_RESERVA, e.getMessage());
        }

    }

    @Test
    void realizarReserva() throws LivroException{
        Usuario usuariotst = new Usuario("nome teste","Rua teste", 7534682,100, "Liberado");
        Usuario usuario2tst = new Usuario("nteste","Rteste", 7532,101, "Liberado");
        DAO.getLivroDAO().realizarReserva(usuariotst,livro1.getId());
        DAO.getLivroDAO().realizarReserva(usuario2tst,livro1.getId());
        assertEquals(2, livro1.getReservas().size());

    }

    @Test
    void failrealizarReserva() throws LivroException{
        try{
            Usuario usuariotst2 = new Usuario("nome teste","Rua teste", 7534682,100, "Bloqueado");
            DAO.getLivroDAO().realizarReserva(usuariotst2, livro3.getId());
        }catch (LivroException e){
            assertEquals(LivroException.RESERVA, e.getMessage());
        }

    }
}