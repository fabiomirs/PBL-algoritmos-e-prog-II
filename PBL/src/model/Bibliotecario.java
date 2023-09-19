package model;

import java.util.Objects;

public class Bibliotecario extends Pessoa {
    private String cargo;
    private Integer senhaAcesso;

    public Bibliotecario(String nome, String cargo, Integer senhaAcesso) {
        super(nome);
        this.cargo = cargo;
        this.senhaAcesso = senhaAcesso;
    }

    public Bibliotecario(String nome, String cargo, Integer senhaAcesso, int id){
        super(nome, id);
        this.cargo = cargo;
        this.senhaAcesso = senhaAcesso;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(Integer senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bibliotecario that = (Bibliotecario) o;
        return Objects.equals(cargo, that.cargo) && Objects.equals(senhaAcesso, that.senhaAcesso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo, senhaAcesso);
    }
}
