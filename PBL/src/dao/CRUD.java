package dao;

import java.util.List;

public interface CRUD<T, E extends Exception>{
    public T create(T obj) throws E;
    public List<T> read();
    public T update(T obj) throws E;
    public void delete(T obj) throws E;
    public T buscarporId(Integer id) throws E;
}
