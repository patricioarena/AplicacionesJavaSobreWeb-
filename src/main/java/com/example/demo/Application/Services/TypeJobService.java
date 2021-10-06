package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.ITypeJobService;
import com.example.demo.DataAccess.Models.TypeJob;
import com.example.demo.DataAccess.Repository.TypeJobRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class TypeJobService extends GenericService<TypeJob, ObjectId> implements ITypeJobService {

    private TypeJobRepository typeJobRepository;

    @Autowired
    public TypeJobService(TypeJobRepository typeJobRepository){
        this.typeJobRepository = typeJobRepository;
    }

    @Override
    public CrudRepository<TypeJob, ObjectId> getRepository() {
        return typeJobRepository;
    }
}
