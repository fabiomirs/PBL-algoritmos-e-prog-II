package dao.livro;

import dao.CRUD;
import exceptions.LivroException;
import model.Livro;

import java.util.List;

public interface LivroDAO extends CRUD<Livro, LivroException> {
    public List<Livro> buscarporAutor(String autor) throws LivroException;
    public List<Livro> buscarporTitulo(String titulo) throws LivroException;
    public List<Livro> buscarPorIsbn(Integer Isbn) throws LivroException;
    public List<Livro> buscarPorCategoria(String categoria) throws LivroException;


}
