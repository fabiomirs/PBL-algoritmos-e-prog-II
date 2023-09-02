package dao;

import java.util.List;

public interface CRUD<T>{
    public T create(T obj);
    public List<T> read();
    public T update(T obj);
    public T delete(T obj);

}
