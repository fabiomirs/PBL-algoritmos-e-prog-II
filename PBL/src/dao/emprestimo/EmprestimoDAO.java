package dao.emprestimo;

import dao.CRUD;
import exceptions.EmprestimoException;
import model.Emprestimo;
import model.Livro;

import java.util.List;

public interface EmprestimoDAO extends CRUD<Emprestimo, EmprestimoException> {
    public Emprestimo criarEmprestimo(Emprestimo objeto) throws EmprestimoException;
    public List<Emprestimo> LivrosEmprestados() throws EmprestimoException;
    public List<Emprestimo> emprestimosAtrasados() throws EmprestimoException;
    public List<Emprestimo> historicoEmprestimosUsuario(Integer idUsuario) throws EmprestimoException;
    public List<Livro> livrosMaisPopulares() throws EmprestimoException;
    //public List<Emprestimo> buscarEmprestimosporId(Integer id) throws EmprestimoException;
    public Integer calcularMulta(Emprestimo objeto) throws EmprestimoException;
    public Emprestimo renovarEmprestimo(Emprestimo objeto) throws EmprestimoException;
    public void registrarDevolucao(Emprestimo objeto) throws EmprestimoException;

    }
