package by.dmitrui98.service.dao;

import java.util.List;

/**
 * Created by Администратор on 13.04.2017.
 */
public interface BaseService<T, T_ID> {
    List<T> getAll();
    T getById(T_ID id);

    T save(T entity);
    boolean remove(T_ID id);

}
