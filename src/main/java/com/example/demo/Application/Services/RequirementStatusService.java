package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IRequirementStatusService;
import com.example.demo.DataAccess.Models.RequirementStatus;
import com.example.demo.DataAccess.Repository.RequirementStatusRepository;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class RequirementStatusService extends GenericService<RequirementStatus, ObjectId> implements IRequirementStatusService {

    private RequirementStatusRepository requirementStatusRepository;

    public RequirementStatusService(RequirementStatusRepository requirementStatusRepository){
        this.requirementStatusRepository = requirementStatusRepository;
    }

    @Override
    public CrudRepository<RequirementStatus, ObjectId> getRepository() {
        return requirementStatusRepository;
    }
}
