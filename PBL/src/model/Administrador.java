package model;

/**
 * A classe Administrador representa um administrador no sistema de biblioteca.
 * É uma subclasse da classe Bibliotecario.
 */
public class Administrador extends Bibliotecario {

    /**
     * Cria um administrador com o nome, cargo e senha de acesso especificados.
     * @param nome O nome do administrador.
     * @param cargo O cargo do administrador.
     * @param senhaAcesso A senha de acesso do administrador.
     */
    public Administrador(String nome, String cargo, Integer senhaAcesso) {
        super(nome, cargo, senhaAcesso);
    }

    /**
     * Cria um administrador com o nome, cargo, senha de acesso e ID especificados.
     * @param nome O nome do administrador.
     * @param cargo O cargo do administrador.
     * @param senhaAcesso A senha de acesso do administrador.
     * @param id O ID único do administrador.
     */
    public Administrador(String nome, String cargo, Integer senhaAcesso, int id){
        super(nome, cargo, senhaAcesso, id);
    }

}