package com.example.pbl.dao.emprestimo;
import com.example.pbl.exceptions.EmprestimoException;
import com.example.pbl.model.Emprestimo;
import com.example.pbl.model.Livro;
import com.example.pbl.model.Usuario;
import com.example.pbl.usuais.FileManager;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class EmprestimoDAOFile implements EmprestimoDAO{
    private List<Emprestimo> emprestimos;
    private String nomePasta;
    private String nomeArquivo;

    /**
     * Método responsável por criar o arquivo de emprestimos e determinar sua pasta.
     */
    public EmprestimoDAOFile() {
        this.nomeArquivo = "emprestimos.dat";
        this.nomePasta = "Emprestimo";
        this.emprestimos = FileManager.ler(this.nomeArquivo,this.nomePasta);
    }

    /**
     * Método de criação declarado no CRUD, não está sendo aplicado ao empréstimo.
     * @param objeto
     * @return
     */
    public Emprestimo create(Emprestimo objeto){return objeto;};

    public Emprestimo criarEmprestimo(Emprestimo objeto) throws EmprestimoException {

        // Verifica se o usuário possui multa em algum empréstimo em aberto
        for (Emprestimo emprestimoUsuario : emprestimos) {
            if (emprestimoUsuario.getUsuario().getNumIdentificacao() == objeto.getUsuario().getNumIdentificacao() && "Em aberto".equals(emprestimoUsuario.getStatus())) {
                Integer multaUsuario = calcularMulta(emprestimoUsuario);
                if (multaUsuario > 0) {
                    throw new EmprestimoException(EmprestimoException.MULTADO, null);
                }
            }
        }

        if (objeto.getUsuario().getStatusConta().equals("Bloqueado")) {
            throw new EmprestimoException(EmprestimoException.CREATE_1, null);
        }

        Livro livro = objeto.getLivro();

        // Verifica se o usuário já atingiu o limite de 2 empréstimos
        int numEmprestimosAtivos = 0;
        for (Emprestimo emprestimoUsuario : emprestimos) {
            if (emprestimoUsuario.getUsuario().getNumIdentificacao() == objeto.getUsuario().getNumIdentificacao() && "Em aberto".equals(emprestimoUsuario.getStatus())) {
                numEmprestimosAtivos++;
            }
        }

        if (numEmprestimosAtivos > 2) {
            throw new EmprestimoException(EmprestimoException.LIMITE_EMPRESTIMOS, null);
        }

        if ("Emprestado".equals(livro.getStatusLivro())) {
            throw new EmprestimoException(EmprestimoException.CREATE_2, null);
        } else if (!objeto.getLivro().getReservas().isEmpty()) {
            if (objeto.getId().equals(objeto.getLivro().proxReserva().getNumIdentificacao())) {
                livro.setStatusLivro("Emprestado");
                objeto.setStatus("Em aberto");
                this.emprestimos.add(objeto);
                FileManager.salvar(this.emprestimos,this.nomeArquivo,this.nomePasta);
                return objeto;
            } else {
                throw new EmprestimoException(EmprestimoException.CREATE_3, null);
            }
        } else {
            livro.setStatusLivro("Emprestado");
            objeto.setStatus("Em aberto");
            this.emprestimos.add(objeto);
            FileManager.salvar(this.emprestimos,this.nomeArquivo,this.nomePasta);
            return objeto;
        }
    }

    /**
     * Método que retorna a lista de empréstimos.
     * @return A lista de empréstimos existente.
     */
    @Override
    public List<Emprestimo> read() {
        return emprestimos;
    }

    /**
     * Método utilizado para atualizar os dados de um empréstimo.
     * @param objeto O empréstimo que se tem interesse em atualizar.
     * @return O empréstimo atualizado.
     * @throws EmprestimoException Exceção que pode ser lançada ao atualizar
     * um empréstimo.
     */
    @Override
    public Emprestimo update(Emprestimo objeto) throws EmprestimoException{
        int index = this.emprestimos.indexOf(objeto);
        if (index != -1){
            this.emprestimos.set(index, objeto);
            FileManager.salvar(this.emprestimos,this.nomeArquivo,this.nomePasta);
            return objeto;
        }
        throw new EmprestimoException(EmprestimoException.UPDATE, objeto);
    }

    /**
     * Método utilizado para deletar um empréstimo da lista
     * de empréstimos.
     * @param objeto O empréstimo que vai ser deletado
     * @throws EmprestimoException Exceção que pode ser lançada ao deletar
     * um empréstimo.
     */
    @Override
    public void delete(Emprestimo objeto) throws EmprestimoException{
        boolean remove = this.emprestimos.remove(objeto);
        if (!remove){
            throw new EmprestimoException(EmprestimoException.DELETE, objeto);
        }
        FileManager.salvar(this.emprestimos,this.nomeArquivo,this.nomePasta);
    }

    /**
     * Método utilizado para excluir a lista de empréstimos.
     */
    @Override
    public void deleteMany() {
        this.emprestimos.clear();
        FileManager.salvar(this.emprestimos,this.nomeArquivo,this.nomePasta);
    }

    public List<Emprestimo> LivrosEmprestados() throws EmprestimoException {
        List<Emprestimo> emprestimosEmAberto = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if ("Em aberto".equals(emprestimo.getStatus())) {
                emprestimosEmAberto.add(emprestimo);
            }
        }
        if (!emprestimosEmAberto.isEmpty()){
            return emprestimosEmAberto;
        }
        throw new EmprestimoException(EmprestimoException.EMPRESTADOS, null);
    }

    public List<Emprestimo> emprestimosAtrasados() throws EmprestimoException{
        List<Emprestimo> emprestimosAtrasados = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for (Emprestimo emprestimo : emprestimos) {
            LocalDate dataDevolucao = emprestimo.getDataDevolucao();
            if (hoje.isAfter(dataDevolucao)) {
                emprestimosAtrasados.add(emprestimo);
            }
        }
        if (!emprestimosAtrasados.isEmpty()){
            return emprestimosAtrasados;
        }
        throw new EmprestimoException(EmprestimoException.ATRASO, null);
    }

    public List<Emprestimo> historicoEmprestimosUsuario(Integer idUsuario) throws EmprestimoException{//AQUI DEU PAU NO TESTE
        List<Emprestimo> historico = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == idUsuario) {
                historico.add(emprestimo);
            }
        }
        if (!historico.isEmpty()){
            return historico;
        }
        throw new EmprestimoException(EmprestimoException.HISTORICO, null);
    }

    public List<Livro> livrosMaisPopulares() throws EmprestimoException {
        Map<Livro, Integer> mapLivrosEmprestimos = new HashMap<>();

        //preenche o mapa com uma chave, o livro, e um valor que corresponde a quantidade de vezes que o livro foi emprestado
        for (Emprestimo emprestimo : emprestimos) {
            Livro livro = emprestimo.getLivro();
            mapLivrosEmprestimos.put(livro, mapLivrosEmprestimos.getOrDefault(livro, 0) + 1);
        }

        if (!mapLivrosEmprestimos.isEmpty()){
            List<Map.Entry<Livro, Integer>> listaOrdenada = new ArrayList<>(mapLivrosEmprestimos.entrySet());

            listaOrdenada.sort(Comparator.comparing(Map.Entry::getValue));

            Collections.reverse(listaOrdenada);

            List<Livro> top3Livros = new ArrayList<>();
            for (int i = 0; i < Math.min(3, listaOrdenada.size()); i++) {
                top3Livros.add(listaOrdenada.get(i).getKey());
            }
            return top3Livros;
        }
        throw new EmprestimoException(EmprestimoException.POPULAR,null);
    }

    /**
     * Método usado para pegar o último empréstimo de um usuário.
     * @param id Id que corresponde ao usuário.
     * @return  Empréstimo de um usuário específico.
     * @throws EmprestimoException Exceção que pode ser lançada caso
     * o usuário não tenha nenhum empréstimo.
     */
    @Override
    public Emprestimo buscarporId(Integer id) throws EmprestimoException {
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getUsuario().getNumIdentificacao() == (id)) {
                return emprestimo;
            }
        }
        throw new EmprestimoException(EmprestimoException.BUSCA_ID, null);
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
            else{
                return 0;
            }
        }
        throw new EmprestimoException(EmprestimoException.MULTA, objeto);
    }

    public Emprestimo renovarEmprestimo(Emprestimo objeto) throws EmprestimoException{
        Usuario usuario = objeto.getUsuario();
        Integer multa = calcularMulta(objeto);
        if (objeto.getLivro().getReservas().isEmpty()) {
            if (objeto.getStatus().equals("Em aberto")){
                if (multa==0){
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
            else{
                throw new EmprestimoException(EmprestimoException.RENOVAR_3, objeto);
            }

        }
        else{
            throw new EmprestimoException(EmprestimoException.RENOVAR_4, objeto);
        }
    }

    public void registrarDevolucao(Emprestimo objeto) throws EmprestimoException{
        if (objeto != null) {
            Integer multa = calcularMulta(objeto);
            if (objeto.getStatus().equals("Em aberto")){
                if (multa>0){
                    throw new EmprestimoException(EmprestimoException.MULTADO, objeto);
                }
                objeto.getLivro().setStatusLivro("Disponivel");
                objeto.setStatus("Fechado");
                // Verifique se há pessoas na fila
                Livro livro = objeto.getLivro();
                if (!livro.getReservas().isEmpty()) {
                    // Remova o primeiro da fila
                    livro.getReservas().poll();
                }
                return;
            }else{
                throw new EmprestimoException(EmprestimoException.DEVOLUCAO_2, objeto);
            }
        }
        throw new EmprestimoException(EmprestimoException.DEVOLUCAO, objeto);
    }

    /**
     * Método que altera o caminho do arquivo Bibliotecario para realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaTeste() {
        this.nomePasta = "Emprestimo Teste";
        this.nomeArquivo = "emprestimoTeste.dat";
        this.emprestimos = FileManager.ler(this.nomeArquivo,this.nomePasta);
    }

    /**
     * Método que retorna o caminho do arquivo Bibliotecario após realizar testes unitários e de integração.
     */
    @Override
    public void alteraParaPastaPrincipal() {
        this.nomePasta = "Emprestimo";
        this.nomeArquivo = "emprestimo.dat";
        this.emprestimos = FileManager.ler(this.nomeArquivo, this.nomePasta);
    }
}