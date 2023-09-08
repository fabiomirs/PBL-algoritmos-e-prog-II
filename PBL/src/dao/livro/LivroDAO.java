package dao.livro;

import dao.CRUD;
import model.Livro;

import java.util.List;

public interface LivroDAO extends CRUD<Livro> {
    public List<Livro> buscarporAutor(String autor);
    public List<Livro> buscarporTitulo(String titulo);
    public List<Livro> buscarPorIsbn(Integer Isbn);
    public List<Livro> buscarPorCategoria(String categoria);


}
