package com.example.pbl.dao.emprestimo;
import com.example.pbl.dao.CRUD;
import com.example.pbl.exceptions.EmprestimoException;
import com.example.pbl.model.Emprestimo;
import com.example.pbl.model.Livro;
import java.util.List;

/**
 * A interface EmprestimoDAO define operações específicas para acesso a dados relacionados aos empréstimos.
 */
public interface EmprestimoDAO extends CRUD<Emprestimo, EmprestimoException> {

    /**
     * Método utilizado para realizar a criação de um empréstimo.
     * @param objeto Empréstimo que se tem interesse em criar.
     * @return Empréstimo já criado, sendo ele adicionado a lista de empréstimos.
     * @throws EmprestimoException Exceções que podem ser lançadas ao criar
     * um empréstimo.
     */
    public Emprestimo criarEmprestimo(Emprestimo objeto) throws EmprestimoException;

    /**
     * Método usado para pegar todos os livros que estão emprestados.
     * @return Lista com todos os livros que estão emprestados.
     * @throws EmprestimoException Exceção que pode ser lançada caso
     * não exista nenhum empréstimo feito.
     */
    public List<Emprestimo> LivrosEmprestados() throws EmprestimoException;

    /**
     * Método usado para pegar que empréstimos estão atrasados.
     * @return Lista de empréstimos que estão atrasados.
     * @throws EmprestimoException Exceção que pode ser lançada caso
     * não exista empréstimo atrasado.
     */
    public List<Emprestimo> emprestimosAtrasados() throws EmprestimoException;

    /**
     * Método usado para pegar o histórico de empréstimos de um usuário.
     * @param idUsuario Id que corresponde ao usuário.
     * @return Lista de empréstimos de um usuário específico.
     * @throws EmprestimoException Exceção que pode ser lançada caso
     * o usuário não tenha nenhum empréstimo.
     */
    public List<Emprestimo> historicoEmprestimosUsuario(Integer idUsuario) throws EmprestimoException;

    /**
     * Método usado para pegar a lista de livros mais populares.
     * @return Lista com os 3 livros mais pegados na biblioteca.
     * @throws EmprestimoException Exceção que pode ser lançada caso
     * não tenha livros com empréstimo feito.
     */
    public List<Livro> livrosMaisPopulares() throws EmprestimoException;

    /**
     * Método usado para calcular a multa caso exista atraso na devolução de
     * um empréstimo.
     * @param objeto Empréstimo para ser verificado atraso.
     * @return Valor da multa.
     * @throws EmprestimoException Exceção que pode ser lançada caso não exista
     * o empréstimo.
     */
    public Integer calcularMulta(Emprestimo objeto) throws EmprestimoException;

    /**
     * Método usado para realizar a renovação de empréstimo de um livro.
     * @param objeto Empréstimo que deseja fazer a renovação.
     * @return Empréstimo com a data de devolução renovada ou não.
     * @throws EmprestimoException Exceção que pode ser lançada ao renovar.
     */
    public Emprestimo renovarEmprestimo(Emprestimo objeto) throws EmprestimoException;

    /**
     * Método usado para fazer a devolução de um empréstimo.
     * @param objeto OEmpréstimo que deseja fazer a devolução.
     * @throws EmprestimoException Exceção que pode ser lançada ao devolver um livro.
     */
    public void registrarDevolucao(Emprestimo objeto) throws EmprestimoException;

    void alteraParaPastaTeste();

    void alteraParaPastaPrincipal();
}
