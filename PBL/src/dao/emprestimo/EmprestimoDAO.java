package dao.emprestimo;

import dao.CRUD;
import model.Emprestimo;

import java.util.List;

public interface EmprestimoDAO extends CRUD<Emprestimo> {
    public List<Emprestimo> buscarEmprestimosporId(Integer id);

    }
