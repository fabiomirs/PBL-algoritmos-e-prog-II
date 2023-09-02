// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import model.Livro;

public class Main {
    public static void main(String[] args) {
        Livro livro1 = new Livro("Título 1", "Editora 1", 1234567890, "Localização A",
                "Disponível", "Autor 1", "2023");
        livro1.toString();
        System.out.println("ID do livro 1: " + livro1.getId());

        Livro livro2 = new Livro("Título 2", "Editora 2", 987654321, "Localização B",
                "Emprestado", "Autor 2", "2020");
        livro2.toString();
        System.out.println("ID do livro 2: " + livro2.getId());
        Livro livro3 = new Livro("Tíggtulo 2", "Editfgfgora 2", 987654321, "Locagglização B",
                "Emprggestado", "Autoggr 2", "20gg20");
        livro2.toString();
        System.out.println("ID do livro 2: " + livro3.getId());

    }
}