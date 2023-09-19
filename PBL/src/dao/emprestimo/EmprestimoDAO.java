package dao.emprestimo;

import dao.CRUD;
import exceptions.EmprestimoException;
import model.Emprestimo;

import java.util.List;

public interface EmprestimoDAO extends CRUD<Emprestimo, EmprestimoException> {
    public Emprestimo criarEmprestimo(Emprestimo objeto) throws EmprestimoException;

    public List<Emprestimo> buscarEmprestimosporId(Integer id) throws EmprestimoException;
    public Integer calcularMulta(Emprestimo objeto) throws EmprestimoException;
    public Emprestimo renovarEmprestimo(Emprestimo objeto) throws EmprestimoException;
    public Emprestimo registrarDevolucao(Emprestimo objeto) throws EmprestimoException;
    public Emprestimo realizarReserva(Emprestimo objeto) throws EmprestimoException;
    }
