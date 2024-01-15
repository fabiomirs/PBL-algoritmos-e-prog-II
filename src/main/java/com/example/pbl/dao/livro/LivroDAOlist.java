package com.example.pbl.dao.livro;
import com.example.pbl.exceptions.LivroException;
import com.example.pbl.model.Livro;
import com.example.pbl.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe LivroDAOlist implementa os métodos relacionados a interface LivroDAO.
 */
public class LivroDAOlist implements LivroDAO {
    private List<Livro> Livros;
    private int proximoID;

    /**
     * Método responsável por criar a lista de livros e determinar
     * o id inicial como 0.
     */
    public LivroDAOlist() {
        this.Livros = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * @return Retorna o id que será utilizado para a criação do livro.
     */
    private int getProximoID() {
        return this.proximoID++;
    }

    /**
     * Método responsável por atribuir um identificador ao objeto Livro e
     * adicioná-lo a lista de livros.
     * @param objeto Objeto Livro que será criado.
     * @return O objeto criado.
     */
    @Override
    public Livro create(Livro objeto) {
        objeto.setId(this.getProximoID());
        this.Livros.add(objeto);
        return objeto;
    }

    /**
     * Método que retorna a lista de livros.
     * @return A lista de livros existente.
     */
    @Override
    public List<Livro> read() {
        return Livros;
    }

    /**
     * Método utilizado para atualizar um objeto Livro.
     * @param objeto Objeto Livro que vai ser atualizado.
     * @return O objeto atualizado.
     * @throws LivroException Exceção que pode ser lançada, caso o livro não exista.
     */
    @Override
    public Livro update(Livro objeto) throws LivroException {
        int index = this.Livros.indexOf(objeto);
        if (index != -1) {
            this.Livros.set(index, objeto);
            return objeto;
        } else {
            throw new LivroException(LivroException.UPDATE, objeto);
        }
    }

    /**
     * Método utilizado para deletar um objeto Livro da lista existente.
     * @param objeto Objeto que vai ser deletado.
     * @throws LivroException Exceção que pode ser lançada, caso o livro não exista na lista.
     */
    public void delete(Livro objeto) throws LivroException {
        boolean remove = this.Livros.remove(objeto);
        if (!remove) {
            throw new LivroException(LivroException.DELETE, objeto);
        }
    }

    /**
     * Método utilizado para excluir a lista de livros e
     * reinicializar o id como 0.
     */
    @Override
    public void deleteMany() {
        this.Livros = new ArrayList<>();
        this.proximoID = 0;
    }

    /**
     * Método usado para buscar um livro por id.
     * @param id O id a ser buscado.
     * @return O objeto encontrado.
     * @throws LivroException Exceção que pode ser lançada, caso o id não exista na lista.
     */
    public Livro buscarporId(Integer id) throws LivroException {
        for (Livro Livro : this.Livros) {
            if (Livro.getId() == id) {
                return Livro;
            }
        }
        throw new LivroException(LivroException.BUSCA_ID, null);
    }

    /**
     * Método usado para buscar um livro por título.
     * @param titulo O título a ser buscado.
     * @return Uma lista de livros com o título de interesse.
     * @throws LivroException Exceção que pode ser lançada, caso um livro
     * com o título buscado não exista na lista.
     */
    public List<Livro> buscarporTitulo(String titulo) throws LivroException {
        List<Livro> listLivro = new ArrayList<Livro>();
        for (Livro livro : this.Livros) {
            if (livro.getTitulo() == titulo) {
                listLivro.add(livro);
            }
        }
        if (listLivro.isEmpty()) {
            throw new LivroException(LivroException.BUSCA_TITULO, null);
        } else {
            return listLivro;
        }
    }

    /**
     * Método usado para buscar um livro por autor.
     * @param autor O autor a ser buscado.
     * @return Uma lista de livros com o autor de interesse.
     * @throws LivroException Exceção que pode ser lançada, caso um livro
     * com o autor buscado não exista na lista.
     */
    public List<Livro> buscarporAutor(String autor) throws LivroException {
        List<Livro> listLivro = new ArrayList<Livro>();
        for (Livro livro : this.Livros) {
            if (livro.getAutor() == autor) {
                listLivro.add(livro);
            }
        }
        if (listLivro.isEmpty()) {
            throw new LivroException(LivroException.BUSCA_AUTOR, null);
        } else {
            return listLivro;
        }
    }

    /**
     * Método usado para buscar um livro por isbn.
     * @param isbn O isbn a ser buscado.
     * @return Uma lista de livros com o isbn de interesse.
     * @throws LivroException Exceção que pode ser lançada, caso um livro
     * com o isbn buscado não exista na lista.
     */
    public List<Livro> buscarPorIsbn(Integer isbn) throws LivroException {
        List<Livro> listLivro = new ArrayList<>();
        for (Livro livro : this.Livros) {
            if (livro.getCodigoIsbn().equals(isbn)) {
                listLivro.add(livro);
            }
        }
        if (listLivro.isEmpty()) {
            throw new LivroException(LivroException.BUSCA_ISBN, null);
        } else {
            return listLivro;
        }
    }

    /**
     * Método usado para buscar um livro por categoria.
     * @param categoria A categoria a ser buscado.
     * @return Uma lista de livros com a categoria de interesse.
     * @throws LivroException Exceção que pode ser lançada, caso um livro
     * com a categoria buscado não exista na lista.
     */
    public List<Livro> buscarPorCategoria(String categoria) throws LivroException {
        List<Livro> listLivro = new ArrayList<>();
        for (Livro livro : this.Livros) {
            if (livro.getCategoria().equalsIgnoreCase(categoria)) {
                listLivro.add(livro);
            }
        }
        if (listLivro.isEmpty()) {
            throw new LivroException(LivroException.BUSCA_CATEGORIA, null);
        } else {
            return listLivro;
        }
    }

    /**
     * Método utilizado para adicionar um usuário a fila de reservas de um livro.
     * @param objeto O usuário que deseja reservar o livro.
     * @param livroId O id do livro que deseja ser reservado.
     * @return O livro que vai ser reservado.
     * @throws LivroException Exceção que pode ser lançada caso um dos parametros
     * seja nulo ou o usuário está bloqueado.
     */
    public Livro realizarReserva(Usuario objeto, Integer livroId) throws LivroException {
        // Encontre o livro com base no ID fornecido
        Livro obj = buscarporId(livroId);

        if (obj != null && objeto != null && objeto.getStatusConta().equals("Liberado")) {
            obj.addReserva(objeto);
            return obj;
        }

        throw new LivroException(LivroException.RESERVA, obj);
    }

    /**
     * Metódo usado para buscar os livros com reserva.
     * @return A lista de livros que estão reservados.
     * @throws LivroException Exceção que pode ser lançada caso nenhum livro
     * possua uma reserva.
     */
    public List<Livro> livrosComReserva() throws LivroException {
        List<Livro> livrosComReserva = new ArrayList<>();
        for (Livro livro : Livros) {
            if (!livro.getReservas().isEmpty()) {
                livrosComReserva.add(livro);
            }
        }
        if (livrosComReserva.isEmpty()) {
            throw new LivroException(LivroException.QTD_RESERVA, null);
        } else {
            return livrosComReserva;
        }

    }

    @Override
    public void alteraParaPastaTeste() {

    }

    @Override
    public void alteraParaPastaPrincipal() {

    }
}

