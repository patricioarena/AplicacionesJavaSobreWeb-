package com.example.demo.Application.IServices;

import java.util.List;

public interface IGenericService<T, ID> {
    T save(T entity);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();
}
