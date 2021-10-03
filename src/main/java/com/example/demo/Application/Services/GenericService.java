package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IGenericService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericService <T, ID extends Serializable> implements IGenericService<T, ID> {
    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getRepository().findById(id);
        if (obj.isPresent()){
            return obj.get();
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> returnList = new ArrayList<>();
        getRepository().findAll().forEach(obj -> returnList.add(obj));
        return returnList;
    }

    public abstract CrudRepository<T, ID> getRepository();
}
