package model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String categoria;
    private List<Livro> livros;
    public Categoria(String categoria){
        this.categoria = categoria;
        this.livros = new ArrayList<>();
    }

    public String getCategoria() {
        return categoria;
    }

    public List<Livro> getLivros() {
        return livros;
    }
    public void addLivro(Livro livro){
        livros.add(livro);
    }
}

