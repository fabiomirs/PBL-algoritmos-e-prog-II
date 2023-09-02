package model;

public class Usuario extends Pessoa {
    private String endereco;
    private Integer telefone;
    private String statusConta;
    private Integer limReservas;

    public Usuario(String nome, Integer numIdentificacao ,String endereco, Integer telefone, String statusConta, Integer limReservas) {
        super(nome, numIdentificacao);
        this.endereco = endereco;
        this.telefone = telefone;
        this.statusConta = statusConta;
        this.limReservas = limReservas;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(String statusConta) {
        this.statusConta = statusConta;
    }

    public Integer getLimReservas() {
        return limReservas;
    }

    public void setLimReservas(Integer limReservas) {
        this.limReservas = limReservas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "endereco='" + endereco + '\'' +
                ", telefone=" + telefone +
                ", statusConta='" + statusConta + '\'' +
                ", limReservas=" + limReservas +
                '}';
    }
}
