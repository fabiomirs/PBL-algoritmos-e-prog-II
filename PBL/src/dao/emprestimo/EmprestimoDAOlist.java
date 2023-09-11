package dao.emprestimo;

import model.Emprestimo;
import model.Livro;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EmprestimoDAOlist implements EmprestimoDAO {
    private List<Emprestimo> emprestimos;
    
    public EmprestimoDAOlist() {
        this.emprestimos = new ArrayList<>();
    }

    @Override
    public Emprestimo create(Emprestimo objeto) {
        if (objeto.getUsuario().getStatusConta().equals("Bloqueado")) {
            return null;
        }

        Livro livro = objeto.getLivro();

        if ("Emprestado".equals(livro.getStatusLivro())) {
            return null;
        } else if (!objeto.getLivro().getReservas().isEmpty()) {
            Emprestimo reserva = livro.proxReserva();
            if (reserva != null && reserva.getUsuario().getNumIdentificacao().equals(objeto.getUsuario().getNumIdentificacao())) {
                livro.setStatusLivro("Emprestado");
                this.emprestimos.add(objeto);
                livro.tiraFilareserva();
                return objeto;
            } else {
                return null;
            }
        } else {
            livro.setStatusLivro("Emprestado");
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

    public List<Emprestimo> buscarEmprestimosporId(Integer id) {
        List<Emprestimo> listEmprestimos = new ArrayList<>();
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getId().equals(id)) {
                listEmprestimos.add(emprestimo);
            }
        }
        return listEmprestimos;
    }

    public Integer calcularMulta(Emprestimo objeto) {
        if (objeto != null) {
            LocalDate dataDevolucao = objeto.getDataDevolucao();
            LocalDate entregaFeita = LocalDate.now();

            Integer diasAtraso = Math.toIntExact(ChronoUnit.DAYS.between(dataDevolucao, entregaFeita));
            if (diasAtraso > 0) {
                Integer valorMulta = diasAtraso * 2;
                return valorMulta;
            }
        }
        return 0;
    }

    public Emprestimo renovarEmprestimo(Emprestimo objeto) {
        return null;
    }

    public Emprestimo registrarDevolucao(Emprestimo objeto) {
        if (objeto != null) {
            if (objeto.getLivro().getStatusLivro().equals("Emprestado")) {
                objeto.getLivro().setStatusLivro("Disponivel");
                if (objeto.getLivro().proxReserva() != null){
                    Emprestimo proximo = objeto.getLivro().proxReserva();

                    objeto.getLivro().tiraFilareserva();
                    return proximo;
                }
            }
        }
        return null;
    }

    public Emprestimo realizarReserva(Emprestimo objeto) {
        if (objeto!=null){
                objeto.getLivro().addReserva(objeto);
                return objeto;
        }
        return null;
    }
    
}
