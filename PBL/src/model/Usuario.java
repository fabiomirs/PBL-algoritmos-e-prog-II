package model;

public class Usuario {
    private String endereco;
    private Integer telefone;
    private String statusConta;
    private Integer limReservas;

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
    public void realizarReserva(){

    }
    public void renovarEmprestimo(){

    }
}
