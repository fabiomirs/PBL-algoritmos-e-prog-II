package dao;

import livro.LivroDAO;
import livro.LivroDAOlist;

public class DAO {
    private static LivroDAO livroDAO;
    public static LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            livroDAO = new LivroDAOlist();
        }
        return livroDAO;
    }

}
