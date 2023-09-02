package dao.livro;

import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDAOlist implements LivroDAO {
    private List<Livro> Livros;
    private int proximoID;
    public LivroDAOlist() {
        this.Livros = new ArrayList<>();
        this.proximoID = 0;
    }
    private int getProximoID() {
        return this.proximoID++;
    }

    public Livro create(Livro objeto) {
        objeto.setId(this.getProximoID());
        this.Livros.add(objeto);
        return objeto;
    }

    public List<Livro> read() {
        return Livros;
    }

    public Livro update(Livro objeto) {
        int index = this.Livros.indexOf(objeto);
        this.Livros.set(index, objeto);
        return objeto;
    }

    public void delete(Livro objeto) {
        this.Livros.remove(objeto);
    }

    public Livro buscarporId(Integer id) {
        for (Livro Livro : this.Livros) {
            if (Livro.getId() == id) {
                return Livro;
            }
        }
        return null;
    }

}

