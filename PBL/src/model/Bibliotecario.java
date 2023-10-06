package model;

import java.util.Objects;

/**
 * A classe Bibliotecário representa um bibliotecário no sistema de biblioteca.
 * É uma subclasse da classe Pessoa.
 */
public class Bibliotecario extends Pessoa {
    /**
     * cargo correspondente ao objeto criado.
     */
    private String cargo;
    /**
     * Senha de acesso criada.
     */
    private Integer senhaAcesso;

    /**
     * Cria um bibliotecário com o nome, cargo e senha de acesso especificados.
     * @param nome O nome do bibliotecário.
     * @param cargo O cargo do bibliotecário.
     * @param senhaAcesso A senha de acesso do bibliotecário.
     */
    public Bibliotecario(String nome, String cargo, Integer senhaAcesso) {
        super(nome);
        this.cargo = cargo;
        this.senhaAcesso = senhaAcesso;
    }

    /**
     * Cria um bibliotecário com o nome, cargo, senha de acesso e ID especificados.
     * @param nome O nome do bibliotecário.
     * @param cargo O cargo do bibliotecário.
     * @param senhaAcesso A senha de acesso do bibliotecário.
     * @param id O ID único do bibliotecário.
     */
    public Bibliotecario(String nome, String cargo, Integer senhaAcesso, int id){
        super(nome, id);
        this.cargo = cargo;
        this.senhaAcesso = senhaAcesso;
    }

    /**
     * Obtém o cargo do bibliotecário.
     * @return O cargo do bibliotecário.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Determina o cargo do bibliotecário.
     * @param cargo O cargo do bibliotecário a ser determinado.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Obtém a senha de acesso do bibliotecário.
     * @return A senha de acesso do bibliotecário.
     */
    public Integer getSenhaAcesso() {
        return senhaAcesso;
    }

    /**
     * Determina a senha de acesso do bibliotecário.
     * @param senhaAcesso A senha de acesso do bibliotecário a ser determinada.
     */
    public void setSenhaAcesso(Integer senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    /**
     * Compara este bibliotecário com outro objeto para determinar se são iguais.
     * @param o O objeto a ser comparado.
     * @return True se os objetos são iguais, False caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bibliotecario that = (Bibliotecario) o;
        return Objects.equals(cargo, that.cargo) && Objects.equals(senhaAcesso, that.senhaAcesso);
    }
}
