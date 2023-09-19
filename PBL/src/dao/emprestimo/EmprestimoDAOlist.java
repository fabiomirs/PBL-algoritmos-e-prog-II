package dao.emprestimo;

import exceptions.EmprestimoException;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.List;


public class EmprestimoDAOlist implements EmprestimoDAO {
    private List<Emprestimo> emprestimos;
    
    public EmprestimoDAOlist() {
        this.emprestimos = new ArrayList<>();
    }

    public Emprestimo create(Emprestimo objeto){return objeto;};

    @Override
    public Emprestimo criarEmprestimo(Emprestimo objeto) throws EmprestimoException{
        if (objeto.getUsuario().getStatusConta().equals("Bloqueado")) {
            throw new EmprestimoException(EmprestimoException.CREATE_1, null);
        }

        Livro livro = objeto.getLivro();

        if ("Emprestado".equals(livro.getStatusLivro())) {
            throw new EmprestimoException(EmprestimoException.CREATE_2, null);
        } else if (!objeto.getLivro().getReservas().isEmpty()) {
            Emprestimo reserva = livro.proxReserva();
            if (reserva != null && reserva.getUsuario().getNumIdentificacao().equals(objeto.getUsuario().getNumIdentificacao())) {
                livro.setStatusLivro("Emprestado");
                this.emprestimos.add(objeto);
                livro.tiraFilareserva();
                return objeto;
            } else {
                throw new EmprestimoException(EmprestimoException.CREATE_3, null);
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
    public Emprestimo update(Emprestimo objeto) throws EmprestimoException{
        int index = this.emprestimos.indexOf(objeto);
        if (index != -1){
            this.emprestimos.set(index, objeto);
            return objeto;
        }
        throw new EmprestimoException(EmprestimoException.UPDATE, objeto);
    }

    @Override
    public void delete(Emprestimo objeto) throws EmprestimoException{
        boolean remove = this.emprestimos.remove(objeto);
        if (!remove){
            throw new EmprestimoException(EmprestimoException.DELETE, objeto);
        }
    }

    @Override
    public void deleteMany() {
        this.emprestimos = new ArrayList<>();
    }

    @Override
    public Emprestimo buscarporId(Integer id) throws EmprestimoException {
        for (int i = this.emprestimos.size() - 1; i >= 0; i--) {
            Emprestimo emprestimo = this.emprestimos.get(i);
            if (emprestimo.getId().equals(id)) {
                return emprestimo;
            }
        }
        throw new EmprestimoException(EmprestimoException.BUSCA_ID, null);
    }



    public List<Emprestimo> buscarEmprestimosporId(Integer id) throws EmprestimoException{
        List<Emprestimo> listEmprestimos = new ArrayList<>();
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getId().equals(id)) {
                listEmprestimos.add(emprestimo);
            }
        }
        if (!listEmprestimos.isEmpty()){
            return listEmprestimos;
        }
        throw new EmprestimoException(EmprestimoException.BUSCAS_POR_ID, null);
    }

    public Integer calcularMulta(Emprestimo objeto) throws EmprestimoException{
        if (objeto != null) {
            LocalDate dataDevolucao = objeto.getDataDevolucao();
            LocalDate entregaFeita = LocalDate.now();

            Integer diasAtraso = Math.toIntExact(ChronoUnit.DAYS.between(dataDevolucao, entregaFeita));
            if (diasAtraso > 0) {
                Integer valorMulta = diasAtraso * 2;
                return valorMulta;
            }
        }
        throw new EmprestimoException(EmprestimoException.MULTA, objeto);
    }

    public Emprestimo renovarEmprestimo(Emprestimo objeto) throws EmprestimoException{
        Usuario usuario = objeto.getUsuario();
        if (objeto.getLivro().getReservas().isEmpty()) {
            if (objeto.getUsuario().getLimRenovacao() > 0) {
                LocalDate novaDataDevolucao = objeto.getDataDevolucao().plusDays(7);
                objeto.setDataDevolucao(novaDataDevolucao);

                usuario.setlimRenovacao(usuario.getLimRenovacao() - 1);
                return objeto;
            }
            else{
                throw new EmprestimoException(EmprestimoException.RENOVAR_1, objeto);
            }
        }
        else{
            throw new EmprestimoException(EmprestimoException.RENOVAR_2, objeto);
        }
    }


    public Emprestimo registrarDevolucao(Emprestimo objeto) throws EmprestimoException{
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
        throw new EmprestimoException(EmprestimoException.DEVOLUCAO, objeto);
    }

    public Emprestimo realizarReserva(Emprestimo objeto) throws EmprestimoException{
        if (objeto!=null){
                objeto.getLivro().addReserva(objeto);
                return objeto;
        }
        throw new EmprestimoException(EmprestimoException.RESERVA, objeto);
    }
    
}
