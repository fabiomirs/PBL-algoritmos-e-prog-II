package com.example.pbl.dao.livro;
import com.example.pbl.dao.CRUD;
import com.example.pbl.exceptions.LivroException;
import com.example.pbl.model.Livro;
import com.example.pbl.model.Usuario;
import java.util.List;

/**
 * A interface LivroDAO define operações específicas para acesso a dados relacionados aos livros.
 */
public interface LivroDAO extends CRUD<Livro, LivroException> {
    public List<Livro> buscarporAutor(String autor) throws LivroException;
    public List<Livro> buscarporTitulo(String titulo) throws LivroException;
    public List<Livro> buscarPorIsbn(Integer Isbn) throws LivroException;
    public List<Livro> buscarPorCategoria(String categoria) throws LivroException;
    public Livro realizarReserva(Usuario objeto, Integer IdLivro) throws LivroException;
    public List<Livro> livrosComReserva() throws LivroException;


    void alteraParaPastaTeste();

    void alteraParaPastaPrincipal();
}
