package model;

public class Bibliotecario extends Pessoa {
    private String cargo;
    private Integer senhaAcesso;

    public Bibliotecario(String nome, Integer numIdentificacao, String cargo, Integer senhaAcesso) {
        super(nome, numIdentificacao);
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
}
