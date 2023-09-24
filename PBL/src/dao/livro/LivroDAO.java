package dao.livro;

import dao.CRUD;
import exceptions.EmprestimoException;
import exceptions.LivroException;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.util.List;

public interface LivroDAO extends CRUD<Livro, LivroException> {
    public List<Livro> buscarporAutor(String autor) throws LivroException;
    public List<Livro> buscarporTitulo(String titulo) throws LivroException;
    public List<Livro> buscarPorIsbn(Integer Isbn) throws LivroException;
    public List<Livro> buscarPorCategoria(String categoria) throws LivroException;
    public Livro realizarReserva(Usuario objeto, Livro obj) throws LivroException;
    public List<Livro> livrosComReserva() throws LivroException;


}
