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

class EmprestimoDAOlistTest {
    Livro livro1;
    Livro livro2;
    Livro livro3;
    Livro livro4;
    Usuario U1;
    Usuario U2;
    Usuario U3;
    Emprestimo emprestimo1;
    Emprestimo emprestimo2;
    Emprestimo emprestimo3;
    Emprestimo emprestimo4;



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

    @AfterEach
    void tearDown(){
        DAO.getEmprestimoDAO().deleteMany();
        DAO.getUsuarioDAO().deleteMany();
        DAO.getLivroDAO().deleteMany();
    }

    @Test
    void criarEmprestimo() throws EmprestimoException{
        Usuario userTeste = new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777, 3, "Nloqueado");
        Livro livrotest = new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao", 4);

        Emprestimo esperado = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livrotest, userTeste));

        assertNotNull(esperado);
        assertEquals(3, esperado.getId());
    }

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

    @Test
    void criarEmprestimoLimiteAtingido() throws EmprestimoException{
        try{
            Livro livroTeste = DAO.getLivroDAO().create(new Livro("Livro Novo", "Editora Nova", 4, "setor a", "andre", "Dec 1, 2023", "educacao"));
            DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livroTeste, U1));
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.LIMITE_EMPRESTIMOS, e.getMessage());
        }
    }

    @Test
    void read() {
        DAO.getEmprestimoDAO().read();
        assertEquals(4, DAO.getEmprestimoDAO().read().size());
    }

    @Test
    void update() throws EmprestimoException {
        emprestimo3.setStatus("Fechado");
        Emprestimo atual = DAO.getEmprestimoDAO().update(emprestimo3);
        assertEquals(emprestimo3, atual);
    }

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

    @Test
    void delete() throws EmprestimoException{
        int tamanho_esperado = DAO.getEmprestimoDAO().read().size();
        DAO.getEmprestimoDAO().delete(emprestimo2);
        assertEquals(tamanho_esperado-1, DAO.getEmprestimoDAO().read().size());
    }

    @Test
    void failDelete() throws EmprestimoException {
        try {
            DAO.getEmprestimoDAO().delete(new Emprestimo(livro2,U3));
            fail("Uma exceção deveria ser gerada!!");
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.DELETE, e.getMessage());
        }

    }

    @Test
    void livrosMaisPopulares() throws EmprestimoException{
        Usuario userteste = new Usuario("FabioBli", "Ap #124-2996 A Avenue", 7777, 3, "Liberado");
        DAO.getEmprestimoDAO().registrarDevolucao(emprestimo2);
        Emprestimo emprestimo5 = DAO.getEmprestimoDAO().criarEmprestimo(new Emprestimo(livro2, userteste));

        assertEquals(livro2, DAO.getEmprestimoDAO().livrosMaisPopulares().get(0));
    }

    @Test
    void faillivrosMaisPopulares() throws EmprestimoException{
        try{
            DAO.getEmprestimoDAO().deleteMany();
            DAO.getEmprestimoDAO().livrosMaisPopulares();
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.POPULAR, e.getMessage());
        }
    }

    @Test
    void LivrosEmprestados() throws EmprestimoException {
        int esperado = 4;
        assertEquals(esperado, DAO.getEmprestimoDAO().LivrosEmprestados().size());
    }

    @Test
    void failLivrosEmprestados() throws EmprestimoException{
        try{
            DAO.getEmprestimoDAO().deleteMany();
            DAO.getEmprestimoDAO().LivrosEmprestados();
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.EMPRESTADOS, e.getMessage());
        }
    }


    @Test
    void emprestimosAtrasados() throws EmprestimoException {
        // cenário onde há empréstimos atrasados
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(1));
        emprestimo2.setDataDevolucao(LocalDate.now().minusDays(2));

        int esperado = 2;
        assertEquals(esperado, DAO.getEmprestimoDAO().emprestimosAtrasados().size());
    }

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


    @Test
    void historicoEmprestimosUsuario() throws EmprestimoException{
        assertNotNull( DAO.getEmprestimoDAO().historicoEmprestimosUsuario(0));

        assertEquals(2, DAO.getEmprestimoDAO().historicoEmprestimosUsuario(0).size());
    }

    @Test
    void failHistoricoUsuario() throws EmprestimoException{
        try{
            DAO.getEmprestimoDAO().historicoEmprestimosUsuario(51);
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.HISTORICO, e.getMessage());
        }
    }

    @Test
    void buscarporId() throws EmprestimoException {
        Integer idExistente = 1;

        Emprestimo resultado = DAO.getEmprestimoDAO().buscarporId(idExistente);
        assertNotNull(resultado);
        assertEquals(idExistente, resultado.getId());
    }

    @Test
    void failbuscarporId() {
        Integer idExistente = 99;

        try {
            Emprestimo resultado = DAO.getEmprestimoDAO().buscarporId(idExistente);
            assertNotNull(resultado);
            assertEquals(idExistente, resultado.getId());
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.BUSCA_ID, e.getMessage());
        }
    }

    @Test
    void calcularMulta() throws EmprestimoException{
        emprestimo1.setDataDevolucao(LocalDate.now().minusDays(2));
        assertEquals(4, DAO.getEmprestimoDAO().calcularMulta(emprestimo1));

        assertEquals(0,DAO.getEmprestimoDAO().calcularMulta(emprestimo2));

    }

    @Test
    void failcalcularMullta() throws EmprestimoException{
        try{
            Emprestimo emprestimoTeste = new Emprestimo(livro4, U2);
            DAO.getEmprestimoDAO().calcularMulta(emprestimoTeste);
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.MULTA, e.getMessage());
        }
    }

    @Test
    void renovarEmprestimo() throws EmprestimoException{
        DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        LocalDate novaData = LocalDate.now().plusDays(14);
        assertEquals(novaData, emprestimo1.getDataDevolucao());
    }

    @Test
    void renovarEmprestimoLimiteExcedido() {
        emprestimo1.setStatus("Em aberto");
        emprestimo1.getLivro().getReservas().clear(); // Reservas vazias
        emprestimo1.getUsuario().setlimRenovacao(0); // Limite de renovação atingido

        try {
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENOVAR_1, e.getMessage());
        }
    }

    @Test
    void renovarEmprestimoUsuarioMultado() {
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

    @Test
    void renovarEmprestimoEmAberto() {
        emprestimo1.setStatus("Em aberto");

        try {
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENOVAR_3, e.getMessage());
        }
    }

    @Test
    void renovarEmprestimoComReservas() {
        emprestimo1.setStatus("Em aberto");
        emprestimo1.getLivro().getReservas().add(U3);

        try {
            DAO.getEmprestimoDAO().renovarEmprestimo(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.RENOVAR_4, e.getMessage());
        }
    }



    @Test
    void registrarDevolucao() throws EmprestimoException{
        DAO.getEmprestimoDAO().registrarDevolucao(emprestimo1);
        DAO.getEmprestimoDAO().registrarDevolucao(emprestimo2);

        assertEquals(2, DAO.getEmprestimoDAO().LivrosEmprestados().size());
    }


    @Test
    void registrarDevolucaoEmAberto() {
        emprestimo1.setStatus("Em aberto");

        try {
            DAO.getEmprestimoDAO().registrarDevolucao(emprestimo1);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.DEVOLUCAO_2, e.getMessage());
        }
    }

    @Test
    void registrarDevolucaoComMulta() throws EmprestimoException{
        try{
            emprestimo1.setDataDevolucao(emprestimo1.getDataEmprestimo().minusDays(1));
            DAO.getEmprestimoDAO().registrarDevolucao(emprestimo1);
        }catch (EmprestimoException e){
            assertEquals(EmprestimoException.MULTADO, e.getMessage());
        }
    }

    @Test
    void registrarDevolucaoNulo() {
        try {
            DAO.getEmprestimoDAO().registrarDevolucao(null);
        } catch (EmprestimoException e) {
            assertEquals(EmprestimoException.DEVOLUCAO, e.getMessage());
        }
    }

}