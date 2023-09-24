package model;

import java.util.Objects;

public class Usuario extends Pessoa {
    private String endereco;
    private Integer telefone;
    private String statusConta;
    private Integer limRenovacao;


    public Usuario(String nome ,String endereco, Integer telefone, int id, String statusConta) {
        super(nome, id);
        this.endereco = endereco;
        this.telefone = telefone;
        this.statusConta = statusConta;
        this.limRenovacao = 2;
    }

    public Usuario(String nome ,String endereco, Integer telefone) {
        super(nome);
        this.endereco = endereco;
        this.telefone = telefone;
        this.statusConta = "Liberado";
        this.limRenovacao = 2;
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

    public Integer getLimRenovacao() {
        return limRenovacao;
    }

    public void setlimRenovacao(Integer limRenovacao) {
        this.limRenovacao= limRenovacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "endereco='" + endereco + '\'' +
                ", telefone=" + telefone +
                ", statusConta='" + statusConta + '\'' +
                ", limReservas=" + limRenovacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(endereco, usuario.endereco) && Objects.equals(telefone, usuario.telefone) && Objects.equals(statusConta, usuario.statusConta) && Objects.equals(limRenovacao, usuario.limRenovacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), endereco, telefone, statusConta, limRenovacao);
    }
}
