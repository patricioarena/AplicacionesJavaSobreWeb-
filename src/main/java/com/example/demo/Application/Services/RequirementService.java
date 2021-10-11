package com.example.demo.Application.Services;


import com.example.demo.Application.IServices.IRequirementService;
import com.example.demo.DataAccess.Models.Requirement;
import com.example.demo.DataAccess.Repository.RequirementRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class RequirementService extends GenericService<Requirement, ObjectId> implements IRequirementService {

    private RequirementRepository requirementRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @Override
    public CrudRepository<Requirement, ObjectId> getRepository() {
        return requirementRepository;
    }
}
