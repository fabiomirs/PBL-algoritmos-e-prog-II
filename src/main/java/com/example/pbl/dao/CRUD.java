package com.example.pbl.dao;
import java.util.List;

/**
 * A interface CRUD determina operações principais para manipular dados.
 * @param <T> O tipo de objeto que é manipulado.
 * @param <E> A exceção que pode ser gerada.
 */
public interface CRUD<T, E extends Exception>{
    /**
     * Cria um novo objeto.
     * @param obj O objeto a ser criado.
     * @return O objeto já criado.
     */
    public T create(T obj);

    /**
     * Lê e retorna uma lista de todos os objetos armazenados.
     * @return Uma lista de todos os objetos.
     */
    public List<T> read();

    /**
     * Atualiza um objeto existente no armazenamento.
     *
     * @param obj O objeto a ser atualizado.
     * @return O objeto atualizado.
     * @throws E Uma exceção que pode ser lançada durante a operação de atualização.
     */
    public T update(T obj) throws E;

    /**
     * Deleta um objeto existente.
     * @param obj O objeto a ser excluído do armazenamento.
     * @throws E Uma exceção que pode ser lançada durante a operação de exclusão.
     */
    public void delete(T obj) throws E;

    /**
     * Deleta todos os objetos armazenados.
     */
    public void deleteMany();

    /**
     * Busca um objeto pelo seu ID.
     * @param id O ID do objeto a ser buscado.
     * @return O objeto encontrado com o ID especificado.
     * @throws E Uma exceção que pode ser lançada se o objeto não for encontrado.
     */
    public T buscarporId(Integer id) throws E;
}
