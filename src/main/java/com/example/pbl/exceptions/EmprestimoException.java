package com.example.pbl.exceptions;

import com.example.pbl.model.Emprestimo;

/**
 * A classe EmprestimoException é uma exceção personalizada que pode ser lançada relacionada aos empréstimos.
 */
public class EmprestimoException extends Exception{

    /**
     * Mensagens estáticas para as exceções
     */
    public static final String DELETE = "Operação de exclusão não realizada.";

    public static final String UPDATE = "Operação de atualização não realizada.";

    public static final String BUSCA_ID = "O empréstimo não foi encontrado dentre os disponíveis.";

    public static final String CREATE_1 = "O usuário está bloqueado para realizar empréstimos.";
    public static final String CREATE_2 = "O livro já está emprestado a outra pessoa.";
    public static final String CREATE_3 = "O usuário não é o primeiro na fila de espera.";

    public static final String MULTA = "Não é necessário calcular multa para esse usuário.";

    public static final String RENOVAR_1 = "O usuário já atingiu o limite de renovações.";
    public static final String RENOVAR_2 = "O usuário está multado.";

    public static final String RENOVAR_3 = "O empréstimo já foi encerrado.";

    public static final String RENOVAR_4 = "Já existem reservas para esse livro.";

    public static final String MULTADO = "O usuário está multado.";
    public static final String LIMITE_EMPRESTIMOS = "O usuário atingiu o limite de emprestimos em aberto.";
    public static final String EMPRESTADOS = "Não existem empréstimos realizados.";
    public static final String POPULAR = "Ainda não foram contabilizados empréstimos.";
    public static final String ATRASO = "Não existem emprestimos que estão em atraso.";
    public static final String HISTORICO = "O usuário ainda não realizou nenhum empréstimo.";

    public static final String DEVOLUCAO = "A devolução não foi contabilizada, por se tratar de um empréstimo inválido.";
    public static final String DEVOLUCAO_2 = "A devolução não foi contabilizada, por se tratar de um empréstimo já finalizado.";

    private Emprestimo emprestimo;

    /**
     * Cria uma instância de EmprestimoException com uma mensagem de erro específica e uma referência ao emprestimo afetado.
     * @param message A mensagem de erro que descreve a exceção.
     * @param emprestimo O emprestimo relacionado à exceção.
     */
    public EmprestimoException(String message, Emprestimo emprestimo) {
        super(message);
        this.emprestimo = emprestimo;
    }
}
