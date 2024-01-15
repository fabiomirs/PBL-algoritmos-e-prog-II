package dao.livro;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.LivroException;
import com.example.pbl.model.Livro;
import com.example.pbl.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe criada para realizar os testes relacionados ao livroDAOlist.
 */
class LivroDAOTest {
    /**
     * Representa o primeiro livro de teste.
     */
    Livro livro1;
    /**
     * Representa o segundo livro de teste.
     */
    Livro livro2;
    /**
     * Representa o terceiro livro de teste.
     */
    Livro livro3;

    /**
     * Executa ANTES de cada teste.
     */
    @BeforeEach
    void setUp() {
        DAO.getLivroDAO().alteraParaPastaTeste();
        livro1 = DAO.getLivroDAO().create(new Livro("x", "Penguin Random House", 1, "setor e", "joao", "Nov 16, 2022", "teste"));
        livro2 = DAO.getLivroDAO().create(new Livro("b", "HarperCollins", 2, "setor d", "joao", "Mar 2, 2024", "Ficção"));
        livro3 = DAO.getLivroDAO().create(new Livro("c", "Macmillan Publishers", 3, "setor c", "joao", "Nov 5, 2022", "educacao"));
    }

    /**
     * Executa DEPOIS de cada teste.
     */
    @AfterEach
    void tearDown() {
        DAO.getLivroDAO().deleteMany();
        DAO.getLivroDAO().alteraParaPastaPrincipal();
    }

    /**
     * Testa o método create da classe LivroDAO, realizando a comparação entre um livro já existente
     * com o novo livro que vai ser criado. E ainda um teste verficando se o livro criado foi adicionado
     * a lista de livros.
     */
    @Test
    void create() {
        // Primeiro teste
        Livro novoLivro = new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao", 3);
        Livro livroCriado = DAO.getLivroDAO().create(new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao"));

        assertNotNull(livroCriado);
        assertEquals(novoLivro, livroCriado);

        // Segundo teste
        Livro novoLivro2 = new Livro("Livro Novo 2", "Editora Nova", 17, "setor b", "maria", "Dec 2, 2023", "Aventura", 4);
        Livro livroCriado2 = DAO.getLivroDAO().create(new Livro("Livro Novo 2", "Editora Nova", 17, "setor b", "maria", "Dec 2, 2023", "Aventura"));

        assertNotNull(livroCriado2);
        assertEquals(novoLivro2, livroCriado2);

        // Verifica se o livro foi realmente adicionado à lista
        assertTrue(DAO.getLivroDAO().read().contains(novoLivro));
    }

    /**
     * Testa o método read da classe LivroDAO realizando a verificação da quantidade
     * de livros existentes comparando-a com a quantidade de livros que são criados
     * no setup()
     */
    @Test
    void read() {
        DAO.getLivroDAO().read();
        assertEquals(3, DAO.getLivroDAO().read().size());
    }

    /**
     * Testa o método update da classe LivroDAO, verificando se uma atualização está
     * de acordo com a mudança feita.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void update() throws LivroException {
        // Primeiro teste
        livro1.setTitulo("Novo Título");
        livro1.setAutor("Novo Autor");
        Livro livroAtualizado = DAO.getLivroDAO().update(livro1);

        assertNotNull(livroAtualizado);
        assertEquals("Novo Título", livroAtualizado.getTitulo());
        assertEquals("Novo Autor", livroAtualizado.getAutor());

        // Segundo teste
        livro3.setCategoria("Ficção Científica");
        Livro livroAtt2 = DAO.getLivroDAO().update(livro3);

        assertNotNull(livroAtt2);
        assertEquals("Ficção Científica", livroAtt2.getCategoria());

        // Verifica se o livro atualizado está presente na lista
        assertTrue(DAO.getLivroDAO().read().contains(livroAtt2));
    }

    /**
     * Testa o método update da classe LivroDAO com um caso de falha.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void failUpdate() throws LivroException {
        try {
            Livro livrotst = new Livro("Livro teste falho", "Editora falha", 47, "setor j", "andre", "Dec 1, 2023", "educacao", 3);
            livrotst.setAutor("Fábio fail");
            livrotst.setCategoria("esporte");
            livrotst.setEditora("fail");
            DAO.getLivroDAO().update(livrotst);
        } catch (LivroException e) {
            assertEquals(LivroException.UPDATE, e.getMessage());
        }
    }

    /**
     * Testa o método delete da classe LivroDAO, verificando se o tamanaho da lista de livros
     * é alterado de forma correta depois que ocorre uma exclusão, e também verificando se o
     * livro deixa de existir na lista de livros depois de deletado.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void delete() throws LivroException {
        // Primeiro teste
        int tamanho_esperado = DAO.getLivroDAO().read().size();
        DAO.getLivroDAO().delete(livro1);
        assertEquals(tamanho_esperado - 1, DAO.getLivroDAO().read().size());

        // Segundo teste
        DAO.getLivroDAO().delete(livro3);
        assertEquals(tamanho_esperado - 2, DAO.getLivroDAO().read().size());

        // Verifica se o livro não está mais na lista
        assertFalse(DAO.getLivroDAO().read().contains(livro3));
    }

    /**
     * Testa o método delete da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção relacionada ao livro.
     */
    @Test
    void failDelete() throws LivroException {
        try {
            DAO.getLivroDAO().delete(new Livro("z", "Schuster", 99, "setor z", "andre", "Jan 30, 2023", "Romance"));
            fail("Uma exceção deveria ser gerada!!");
        } catch (LivroException e) {
            assertEquals(LivroException.DELETE, e.getMessage());
        }
    }

    /**
     * Testa o método deleteMany da classe LivroDAO, verificando se a quantidade
     * de livros na lista fica igual a zero.
     */
    @Test
    void deleteMany() {
        DAO.getLivroDAO().deleteMany();
        assertEquals(0, DAO.getLivroDAO().read().size());
    }

    /**
     * Testa o método buscarporId da classe LivroDAO, verificando se o livro
     * buscado é igual ao que se espera ter como retorno.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void buscarporId() throws LivroException {
        Livro esperado = livro1;
        Livro atual = DAO.getLivroDAO().buscarporId(esperado.getId());
        assertEquals(esperado, atual);

        Livro esperado_2 = livro3;
        Livro atual_2 = DAO.getLivroDAO().buscarporId(esperado_2.getId());
        assertEquals(esperado_2, atual_2);
    }

    /**
     * Testa o método buscarporId da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção relacionada ao livro não encontrado.
     */
    @Test
    void failbuscarporId() throws LivroException {
        try {
            Livro livrotst = new Livro("Livro teste falho", "Editora falha", 47, "setor j", "andre", "Dec 1, 2023", "educacao", 10);
            DAO.getLivroDAO().buscarporId(10);
        } catch (LivroException e) {
            assertEquals(LivroException.BUSCA_ID, e.getMessage());
        }
    }

    /**
     * Testa o método buscarporTitulo da classe LivroDAO.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void buscarporTitulo() throws LivroException {
        String titulo = "x";
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarporTitulo(titulo);

        assertNotNull(livrosEncontrados);
        assertEquals(1, livrosEncontrados.size());
        assertEquals(titulo, livrosEncontrados.get(0).getTitulo());
    }

    /**
     * Testa o método buscarporTitulo da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção relacionada ao livro não encontrado.
     */
    @Test
    void failbuscarporTitulo() throws LivroException {
        try {
            String titulo = "Titulo teste falho";
            DAO.getLivroDAO().buscarporTitulo(titulo);
        } catch (LivroException e) {
            assertEquals(LivroException.BUSCA_TITULO, e.getMessage());
        }
    }

    /**
     * Testa o método buscarporAutor da classe LivroDAO.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void buscarporAutor() throws LivroException {
        String autor = "joao";
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarporAutor(autor);

        assertNotNull(livrosEncontrados);
        assertEquals(3, livrosEncontrados.size());
        assertTrue(livrosEncontrados.stream().allMatch(l -> l.getAutor().equals(autor)));
    }

    /**
     * Testa o método buscarporAutor da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção relacionada ao livro não encontrado.
     */
    @Test
    void failbuscarporAutor() throws LivroException {
        try {
            String autor = "Autor teste falho";
            DAO.getLivroDAO().buscarporAutor(autor);
        } catch (LivroException e) {
            assertEquals(LivroException.BUSCA_AUTOR, e.getMessage());
        }
    }

    /**
     * Testa o método buscarPorIsbn da classe LivroDAO.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void buscarPorIsbn() throws LivroException {
        int isbn = 2;
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarPorIsbn(isbn);

        assertNotNull(livrosEncontrados);
        assertEquals(1, livrosEncontrados.size());
        assertEquals(isbn, livrosEncontrados.get(0).getCodigoIsbn());
    }

    /**
     * Testa o método buscarPorIsbn da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção relacionada ao livro não encontrado.
     */
    @Test
    void failbuscarPorIsbn() throws LivroException {
        try {
            int codigo = 7864;
            DAO.getLivroDAO().buscarPorIsbn(codigo);
        } catch (LivroException e) {
            assertEquals(LivroException.BUSCA_ISBN, e.getMessage());
        }
    }

    /**
     * Testa o método buscarPorCategoria da classe LivroDAO.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void buscarPorCategoria() throws LivroException {
        String categoria = "teste";
        List<Livro> livrosEncontrados = DAO.getLivroDAO().buscarPorCategoria(categoria);

        assertNotNull(livrosEncontrados);
        assertEquals(1, livrosEncontrados.size());
        assertTrue(livrosEncontrados.stream().allMatch(l -> l.getCategoria().equals(categoria)));
    }

    /**
     * Testa o método buscarPorCategoria da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção relacionada ao livro não encontrado.
     */
    @Test
    void failbuscarPorCategoria() throws LivroException {
        try {
            String categoria = "categoria falha";
            DAO.getLivroDAO().buscarPorCategoria(categoria);
        } catch (LivroException e) {
            assertEquals(LivroException.BUSCA_CATEGORIA, e.getMessage());
        }
    }

    /**
     * Testa o método livrosComReserva da classe LivroDAO.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void LivrosComReserva() throws LivroException {
        Usuario usuariotest = new Usuario("nome teste","Rua teste", 7534682,100, "Liberado");
        DAO.getLivroDAO().realizarReserva(usuariotest, livro3.getId());
        assertEquals(1, DAO.getLivroDAO().livrosComReserva().size());
    }

    /**
     * Testa o método livrosComReserva da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção caso não exista livros com reserva.
     */
    @Test
    void failLivrosComReserva() throws LivroException{
        try {
            DAO.getLivroDAO().livrosComReserva();
        } catch (LivroException e) {
            assertEquals(LivroException.QTD_RESERVA, e.getMessage());
        }
    }

    /**
     * Testa o método realizarReserva da classe LivroDAO.
     * @throws LivroException se ocorrer uma exceção relacionada ao livro.
     */
    @Test
    void realizarReserva() throws LivroException{
        Usuario usuariotst = new Usuario("nome teste","Rua teste", 7534682,100, "Liberado");
        Usuario usuario2tst = new Usuario("nteste","Rteste", 7532,101, "Liberado");
        DAO.getLivroDAO().realizarReserva(usuariotst,livro1.getId());
        DAO.getLivroDAO().realizarReserva(usuario2tst,livro1.getId());
        assertEquals(2, livro1.getReservas().size());
    }

    /**
     * Testa o método realizarReserva da classe LivroDAO com uma operação de falha.
     * @throws LivroException exceção relacionada a impossibilidade de reservar o livro.
     */
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
