package dao.emprestimo;

import model.Emprestimo;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAOlist implements EmprestimoDAO{
    private List<Emprestimo> reservas;
    private List<Emprestimo> emprestimos;

    public EmprestimoDAOlist(){
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }
    @Override
    public Emprestimo create(Emprestimo objeto) {
        if (objeto.getUsuario().getStatusConta().equals("Bloqueado") || objeto.getLivro().getStatusLivro().equals("Emprestado")){
            return null;
        }
        else{
            objeto.getLivro().setStatusLivro("Emprestado");
            this.emprestimos.add(objeto);
            return objeto;
        }
    }

    @Override
    public List<Emprestimo> read() {
        return emprestimos;
    }

    @Override
    public Emprestimo update(Emprestimo obj) {
        return null;
    }

    @Override
    public void delete(Emprestimo objeto) {
        this.emprestimos.remove(objeto);
    }

    @Override
    public Emprestimo buscarporId(Integer id) {
        return null;
    }
    public List<Emprestimo> buscarEmprestimosporId(Integer id){
        List<Emprestimo> listEmprestimos = new ArrayList<>();
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getId().equals(id)) {
                listEmprestimos.add(emprestimo);
            }
        }
        return listEmprestimos;
    }
    public Integer calcularMulta(Emprestimo objeto){
        return null;
    }
    public Emprestimo renovarEmprestimo(Emprestimo objeto){
     return null;
    }
    public Emprestimo registrarDevolucao(Emprestimo objeto){
        return null;
    }
    public Emprestimo realizarReserva(Emprestimo objeto){
        return null;
    }
}
