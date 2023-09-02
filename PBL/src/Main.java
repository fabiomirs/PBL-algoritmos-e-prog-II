// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import dao.DAO;
import model.Livro;

public class Main {
    public static void main(String[] args) {
        DAO.getLivroDAO().create(new Livro("teste","valoriza",456879,"zona norte","ocupado","fabio","00/00/2023"));
        DAO.getLivroDAO().create(new Livro("teste","valggoriza",456879,"zonagg norte","ocupado","fabio","00/00/2023"));
        DAO.getLivroDAO().create(new Livro("teggste","valoriza",456879,"zona norte","ocupado","fabio","00/00/2023"));
        DAO.getLivroDAO().create(new Livro("teste","valoriza",456879,"zona norte","ocupado","fabggio","00/00/2023"));
        //System.out.println(DAO.getLivroDAO().read());
        System.out.println(DAO.getLivroDAO().buscarporId(0));
    }
}