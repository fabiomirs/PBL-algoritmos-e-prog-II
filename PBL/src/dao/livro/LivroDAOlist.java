package dao.livro;


import exceptions.LivroException;

import model.Livro;
import model.Usuario;

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

    public Livro update(Livro objeto) throws LivroException {
        int index = this.Livros.indexOf(objeto);
        if (index != -1) {
            this.Livros.set(index, objeto);
            return objeto;
        } else {
            throw new LivroException(LivroException.UPDATE, objeto);
        }
    }

    public void delete(Livro objeto) throws LivroException {
        boolean remove = this.Livros.remove(objeto);
        if (!remove) {
            throw new LivroException(LivroException.DELETE, objeto);
        }
    }

    @Override
    public void deleteMany() {
        this.Livros = new ArrayList<>();
        this.proximoID = 0;
    }

    public Livro buscarporId(Integer id) throws LivroException {
        for (Livro Livro : this.Livros) {
            if (Livro.getId() == id) {
                return Livro;
            }
        }
        throw new LivroException(LivroException.BUSCA_ID, null);
    }

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

    public Livro realizarReserva(Usuario objeto, Livro obj) throws LivroException {
        if (objeto != null && objeto.getStatusConta().equals("Liberado")) {
            obj.addReserva(objeto);
            return obj;
        }
        throw new LivroException(LivroException.RESERVA, obj);
    }

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
}

