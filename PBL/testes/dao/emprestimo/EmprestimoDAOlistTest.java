package dao.emprestimo;

import dao.DAO;
import exceptions.EmprestimoException;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe criada para realizar os testes relacionados ao EmprestimoDAOlist.
 */
class EmprestimoDAOlistTest {
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
     * Representa o quarto livro de teste.
     */
    Livro livro4;

    /**
     * Representa o primeiro usuário de teste.
     */
    Usuario U1;

    /**
     * Representa o segundo usuário de teste.
     */
    Usuario U2;

    /**
     * Representa o terceiro usuário de teste.
     */
    Usuario U3;

    /**
     * Representa o primeiro emprestimo de teste.
     */
    Emprestimo emprestimo1;

    /**
     * Representa o segundo emprestimo de teste.
     */
    Emprestimo emprestimo2;

    /**
     * Representa o terceiro emprestimo de teste.
     */
    Emprestimo emprestimo3;

    /**
     * Representa o quarto emprestimo de teste.
     */
    Emprestimo emprestimo4;

    /**
     * Executa ANTES de cada teste
     */
    @BeforeEach
    void setUp() throws EmprestimoException {
        livro1 = DAO.getLivroDAO().create(new Livro("x", "Penguin Random House", 1, "setor e", "joao", "Nov 16, 2022", "Romance"));
        livro2 = DAO.getLivroDAO().create(new Livro("b", "HarperCollins", 2, "setor d", "joao", "Mar 2, 2024", "Ficção"));
        livro3 = DAO.getLivroDAO().create(new Livro("c", "Macmillan Publishers", 3, "setor c", "joao", "Nov 5, 2022", "educacao"));
        livro4 = DAO.getLivroDAO().create(new Livro("d", "HarperCollins", 4, "setor c", "joao", "Sep 6, 2023", "Ciência"));

        U1 = DAO.getUsuarioDAO().create(new Usuario("Hedley Griffin", "P.O. Box 571, 8659 Mauris, Rd.", 31495527));
        U2 = DAO.getUsuarioDAO().create(new Usuario("Gail Berry", "3489 Accumsan St.", 72664858));
        U3 = DAO.getUsuarioDAO().create(new Usuario("Gisela Dean", "Ap #124-2996 A Avenue", 53398795));

        emprestimo1 = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livro1, U1));
        emprestimo2 = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livro2, U2));
        emprestimo3 = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livro3, U3));
        emprestimo4 = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livro4, U1));
    }

    /**
     * Executa DEPOIS de cada teste.
     */
    @AfterEach
    void tearDown(){
        DAO.getEmprestimoDAO().deleteMany();
        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
    }

    /**
     * Método utilizado para testar a criação de um empréstimo válido.
     * @throws EmprestimoException Exceção que pode ser lançada, como é um teste que vai dar correto
     * ela não é aplicada.
     */
    @Test
    void criarEmprestimo() throws EmprestimoException{
        Usuario userTeste = new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777, 3, "Nloqueado");
        Livro livrotest = new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao", 4);

        Emprestimo esperado = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livrotest, userTeste));

        assertNotNull(esperado);
        assertEquals(3, esperado.getId());
    }

    /**
     * Método usado para testar a criação de um empréstimo que
     * tenha o status de conta bloqueado.
     * @throws EmprestimoException Exceção para usuário bloqueado.
     */
    @Test
    void criarEmprestimoUsuarioBloqueado() throws EmprestimoException{
        try {
            Usuario userTeste = new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777, 3, "Nloqueado");
            Livro livrotest = new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao", 4);
            DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livrotest, userTeste));
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.CREATE_1, e.getMessage());
        }
    }

    /**
     * Método usado para testar a criação de um empréstimo que
     * tenha o livro já emprestado.
     * @throws EmprestimoException Exceção para livro já esmprestado.
     */
    @Test
    void criarEmprestimoLivroJaEmprestado() throws EmprestimoException{
        try{
            Usuario userTeste = DAO.getUsuarioDAO().create(new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777));
            Livro livroTeste = DAO.getLivroDAO().create(new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao"));

            Emprestimo esperado = new Emprestimo(livroTeste, userTeste,3);
            esperado.getLivro().setStatusLivro("Emprestado");
            esperado.setStatus("Em aberto");

            Emprestimo atual = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livroTeste, userTeste));

            assertEquals(esperado, atual);

        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.CREATE_2, e.getMessage());

        }
    }

    /**
     * Método usado para testar a criação de um empréstimo que
     * o usuário não é o primeiro da fila de reservas, ou seja, não é
     * prioridade.
     * @throws EmprestimoException Exceção para usuário não ser o primeiro da fila.
     */
    @Test
    void criarEmprestimoUsuarioNaoEPrimeiroNasReservas() throws EmprestimoException{
        try {
            Usuario userTeste = DAO.getUsuarioDAO().create(new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777));
            livro3.addReserva(U2);
            emprestimo3.setStatus("Fechado");
            emprestimo3.getLivro().setStatusLivro("Disponivel");
            DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livro3, userTeste));
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.CREATE_3, e.getMessage());
        }
    }

    /**
     * Método usado para testar o caso onde o usuário já atingiu o limite inicial de empréstimos.
     * @throws EmprestimoException Exceção que pode ser gerada para esse caso.
     */
    @Test
    void criarEmprestimoLimiteAtingido() throws EmprestimoException{
        try{
            Livro livroTeste = DAO.getLivroDAO().create(new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao"));
            DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livroTeste, U1));
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.LIMITE_EMPRESTIMOS, e.getMessage());
        }
    }

    /**
     * Método de teste usado para fazer a leitura e verificar se o tamanho da lista
     * de empréstimos corresponde a quantidade que é criada no setup().
     */
    @Test
    void read() {
        DAO.getEmprestimoDAO().read();
        assertEquals(4, DAO.getEmprestimoDAO().read().size());
    }

    /**
     * Método utilizado para fazer uma atualização de dado válida para o empréstimo.
     * @throws EmprestimoException Exceção lançada caso ocorra um erro de atualização do empréstimo.
     */
    @Test
    void update() throws EmprestimoException {
        emprestimo3.setStatus("Fechado");
        Emprestimo atual = DAO.getEmprestimoDAO().update(emprestimo3);
        assertEquals(emprestimo3, atual);
    }

    /**
     * Método utilizado para testar um caso de atualização de dados falho.
     * @throws EmprestimoException Exceção lançada caso ocorra um erro de atualização do empréstimo.
     */
    @Test
    void failUpdate() throws EmprestimoException {
        try {
            Livro livroTest = new Livro("LivroTeste", "Editora", 78, "setor p", "autorTeste", "Dec 1, 2023", "educacao", 4);
            Usuario usuarioTeste = new Usuario("UserTeste", "RuaTeste", 357951, 3, "Liberado");
            Emprestimo emprestTest = new Emprestimo(livroTest, usuarioTeste);
            emprestTest.setStatus("Fechado");
            DAO.getEmprestimoDAO().update(emprestTest);
        }catch (EmprestimoException e) {
            assertEquals(EmprestimoException.UPDATE, e.getMessage());
        }
    }

    /**
     * Método de teste para realizar a exclusão de um emprestimo e garantir a veracidade
     * comparando a quantidade de elementos que existiam antes e depois da exclusão ser feita.
     * @throws EmprestimoException Exceção que pode ser lançada, como é um teste que vai dar certo ela não se aplica.
     */
    @Test
    void delete() throws EmprestimoException{
        int tamanho_esperado = DAO.getEmprestimoDAO().read().size();
        DAO.getEmprestimoDAO().delete(emprestimo2);
        assertEquals(tamanho_esperado-1, DAO.getEmprestimoDAO().read().size());
    }

    /**
     * Teste realizado tentando excluir um empréstimo inválido.
     * @throws EmprestimoException Exceção lançada ao acontecer o erro de exclusão.
     */
    @Test
    void failDelete() throws EmprestimoException {
        try {
            DAO.getEmprestimoDAO().delete(new Emprestimo(livro2,U3));
            fail("Uma exceção deveria ser gerada!!");
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.DELETE, e.getMessage());
        }
    }

    /**
     * Método utilizado para pegar os livros mais populares, verificando se o livro que esta no top 1
     * dos mais populares deve ser aquele que foram feitos mais empréstimos.
     * @throws EmprestimoException Exceção que pode ser lançada em caso de erro.
     */
    @Test
    void livrosMaisPopulares() throws EmprestimoException{
        Usuario userteste = new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777, 3, "Liberado");
        DAO.getEmprestimoDAO().registrarDevolucao(emprestimo2);
        Emprestimo emprestimo5 = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livro2, userteste));

        assertEquals(livro2, DAO.getEmprestimoDAO().livrosMaisPopulares().get(0));
    }

    /**
     * Método com um caso falho para livros mais populares.
     * @throws EmprestimoException Exceção lançada caso ainda não tenham sido feitos empréstimos.
     */
    @Test
    void faillivrosMaisPopulares() throws EmprestimoException{
        try{
            DAO.getEmprestimoDAO().deleteMany();
            DAO.getEmprestimoDAO().livrosMaisPopulares();
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.POPULAR, e.getMessage());
        }
    }

    /**
     * Método teste para verificar os livros emprestados, compara-se se o tamanho da lista de livros
     * emprestados é igual ao esperado.
     * @throws EmprestimoException Exceção que pode ser lançada em caso de erro.
     */
    @Test
    void LivrosEmprestados() throws EmprestimoException {
        int esperado = 4;
        assertEquals(esperado, DAO.getEmprestimoDAO().LivrosEmprestados().size());
    }

    /**
     * Método com um caso falho para pegar a lista de livros emprestados.
     * @throws EmprestimoException Exceção lançada caso ainda não tenham sido feitos empréstimos.
     */
    @Test
    void failLivrosEmprestados() throws EmprestimoException{
        try{
            DAO.getEmprestimoDAO().deleteMany();
            DAO.getEmprestimoDAO().LivrosEmprestados();
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.EMPRESTADOS, e.getMessage());
        }
    }

    /**
     * Método para testar a tentativa de pegar empréstimos que estão em atraso.
     * @throws EmprestimoException Exceção que pode ser lançada em caso de erro.
     */
    @Test
    void emprestimosAtrasados() throws EmprestimoException {
        // cenário onde há empréstimos atrasados
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(1));
        emprestimo2.setDataDevolucao(LocalDate.now().minusDays(2));

        int esperado = 2;
        assertEquals(esperado, DAO.getEmprestimoDAO().emprestimosAtrasados().size());
    }

    /**
     * Método de teste falho para pegar a lista de empréstimos atrasados.
     * @throws EmprestimoException Exceção lançada caso ainda não empréstimos com atraso.
     */
    @Test
    void failemprestimosatrasados() throws EmprestimoException {
        int esperado = 2;
        try {
            assertNotNull(DAO.getEmprestimoDAO().emprestimosAtrasados());
            assertEquals(esperado, DAO.getEmprestimoDAO().emprestimosAtrasados().size());
        }catch (EmprestimoException e) {
            assertEquals(EmprestimoException.ATRASO, e.getMessage());
        }
    }

    /**
     * Método teste para verificar o histórico de um usuário.
     * @throws EmprestimoException Exceção que pode ser lançada caso ocorra um erro.
     */
    @Test
    void historicoEmprestimosUsuario() throws EmprestimoException{
        assertNotNull( DAO.getEmprestimoDAO().historicoEmprestimosUsuario(0));

        assertEquals(2, DAO.getEmprestimoDAO().historicoEmprestimosUsuario(0).size());
    }

    /**
     * Método de teste falho para pegar o histórico de um usuário.
     * @throws EmprestimoException Exceção lançada caso o usuário não tenha empréstimos.
     */
    @Test
    void failHistoricoUsuario() throws EmprestimoException{
        try{
            DAO.getEmprestimoDAO().historicoEmprestimosUsuario(51);
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.HISTORICO, e.getMessage());
        }
    }

    /**
     * Método teste para realizar a busca de empréstimo por id.
     * @throws EmprestimoException Exceção que pode ser lançada em caso de erro.
     */
    @Test
    void buscarporId() throws EmprestimoException {
        Integer idExistente = 1;

        Emprestimo resultado = DAO.getEmprestimoDAO().buscarporId(idExistente);
        assertNotNull(resultado);
        assertEquals(idExistente, resultado.getId());
    }

    /**
     * Método de teste falho para a busca por id.
     * @throws EmprestimoException Exceção lançada caso o empréstimo com o id informado não seja encontrado.
     */
    @Test
    void failbuscarporId() throws EmprestimoException{
        Integer idExistente = 99;

        try {
            Emprestimo resultado = DAO.getEmprestimoDAO().buscarporId(idExistente);
            assertNotNull(resultado);
            assertEquals(idExistente, resultado.getId());
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.BUSCA_ID, e.getMessage());
        }
    }

    /**
     * Método teste para calcular a multa de um usuário.
     * @throws EmprestimoException Exceção que pode ser lançada em caso de erro.
     */
    @Test
    void calcularMulta() throws EmprestimoException{
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(2));
        assertEquals(4, DAO.getEmprestimoDAO().calcularMulta(emprestimo1));

        assertEquals(0,DAO.getEmprestimoDAO().calcularMulta(emprestimo2));

    }

    /**
     * Método com um caso falho de cálculo da multa.
     * @throws EmprestimoException Exceção lançada caso ainda não seja necessário calcular multa para o usuário.
     */
    @Test
    void failcalcularMullta() throws EmprestimoException{
        try{
            Emprestimo emprestimoTeste = new Emprestimo(livro4, U2);
            DAO.getEmprestimoDAO().calcularMulta(emprestimoTeste);
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.MULTA, e.getMessage());
        }
    }

    /**
     * Método teste para a renovação de um empréstimo.
     * @throws EmprestimoException Exceção que pode ser lançada em caso de erro.
     */
    @Test
    void renovarEmprestimo() throws EmprestimoException{
        DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        LocalDate novaData = LocalDate.now().plusDays(14);
        assertEquals(novaData, emprestimo1.getDataDevolucao());
    }

    /**
     * Método de teste para o usuário que tenta renovar um empréstimo, mas já
     * atingiu o limite de renovações.
     * @throws EmprestimoException Exceção lançada caso o usuário tenha atingido o limite.
     */
    @Test
    void renovarEmprestimoLimiteExcedido() throws EmprestimoException{
        emprestimo1.setStatus("Em aberto");
        emprestimo1.getLivro().getReservas().clear(); // Reservas vazias
        emprestimo1.getUsuario().setlimRenovacao(0); // Limite de renovação atingido

        try {
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENOVAR_1, e.getMessage());
        }
    }

    /**
     * Método teste para o usuário que tenta renovar um empréstimo estando multado.
     * @throws EmprestimoException Exceção lançada caso o usuário esteja multado.
     */
    @Test
    void renovarEmprestimoUsuarioMultado() throws EmprestimoException {
        emprestimo1.setStatus("Em aberto");
        emprestimo1.getLivro().getReservas().clear(); // Reservas vazias
        emprestimo1.getUsuario().setlimRenovacao(1); // Limite de renovação disponível

        emprestimo1.setDataDevolucao(emprestimo1.getDataEmprestimo().minusDays(1));

        try {
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENOVAR_2, e.getMessage());
        }
    }

    /**
     * Método de teste para o usuário que tenta renovar um empréstimo que já foi encerrado.
     * @throws EmprestimoException Exceção lançada caso o empréstimo já tenha sido encerrado.
     */
    @Test
    void renovarEmprestimoEmAberto() throws EmprestimoException{
        emprestimo1.setStatus("Fechado");

        try {
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENOVAR_3, e.getMessage());
        }
    }

    /**
     * Método de teste para o caso que o usuário tenta renovar um empréstimo, mas já existem
     * reservas feitas para o livro.
     * @throws EmprestimoException Exceção lançada em caso de já existirem reservas.
     */
    @Test
    void renovarEmprestimoComReservas() throws EmprestimoException {
        emprestimo1.setStatus("Em aberto");
        emprestimo1.getLivro().getReservas().add(U3);

        try {
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENOVAR_4, e.getMessage());
        }
    }

    /**
     * Método com um teste válido de registro de devolução de um livro.
     * @throws EmprestimoException Exceção que pode ser lançada em caso de erro.
     */
    @Test
    void registrarDevolucao() throws EmprestimoException{
        DAO.getEmprestimoDAO().registrarDevolucao(emprestimo1);
        DAO.getEmprestimoDAO().registrarDevolucao(emprestimo2);

        assertEquals(2, DAO.getEmprestimoDAO().LivrosEmprestados().size());
    }

    /**
     * Método de teste para o caso onde o livro já foi devolvido.
     * @throws EmprestimoException Exceção lançada caso o empréstimo já tenha sido encerrado.
     */
    @Test
    void registrarDevolucaoEmAberto() throws EmprestimoException{
        emprestimo1.setStatus("Fechado");

        try {
            DAO.getEmprestimoDAO().registrarDevolucao(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.DEVOLUCAO_2, e.getMessage());
        }
    }

    /**
     * Método teste para o caso onde o usuário devolve um livro e está com uma multa.
     * @throws EmprestimoException Empréstimo caso o empréstimo esteja atrasado, multado.
     */
    @Test
    void registrarDevolucaoComMulta() throws EmprestimoException{
        try{
            emprestimo1.setDataDevolucao(emprestimo1.getDataEmprestimo().minusDays(1));
            DAO.getEmprestimoDAO().registrarDevolucao(emprestimo1);
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.MULTADO, e.getMessage());
        }
    }

    /**
     * Método teste para o caso onde o empréstimo não existe e é feita a tentativa de devolução.
     * @throws EmprestimoException Exceção lançada caso o empréstimo não exista.
     */
    @Test
    void registrarDevolucaoNulo() throws EmprestimoException{
        try {
            DAO.getEmprestimoDAO().registrarDevolucao(null);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.DEVOLUCAO, e.getMessage());
        }
    }

}