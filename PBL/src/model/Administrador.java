package model;

public class Administrador extends Bibliotecario {

    public Administrador(String nome, String cargo, Integer senhaAcesso) {
        super(nome, cargo, senhaAcesso);
    }
    public Administrador(String nome, String cargo, Integer senhaAcesso, int id){
        super(nome, cargo, senhaAcesso, id);
    }

}