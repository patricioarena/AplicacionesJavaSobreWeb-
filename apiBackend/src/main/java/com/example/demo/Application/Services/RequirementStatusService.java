package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IRequirementStatusService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.Offer;
import com.example.demo.DataAccess.Models.RequirementStatus;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.RequirementStatusRepository;
import com.example.demo.Domain.OfferDto;
import com.example.demo.Domain.RequirementStatusDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// TODO: 25/10/2021 Ver lo que se pueda cambiar por mapper

@Service
@Configuration
public class RequirementStatusService extends GenericService<RequirementStatus, ObjectId> implements IRequirementStatusService {

    private final RequirementStatusRepository requirementStatusRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    public RequirementStatusService(RequirementStatusRepository requirementStatusRepository, CommonResourcesRepository commonResourcesRepository){
        this.requirementStatusRepository = requirementStatusRepository;
        this.commonResourcesRepository = commonResourcesRepository;
    }

    public RequirementStatusDto getRequirementStatusById(String id){
        var rStatus = this.getRepository().findById(new ObjectId(id));
        var status = rStatus.get();
        RequirementStatusDto statusDto = new RequirementStatusDto();
        statusDto.set_id(status.getId().toString());
        statusDto.setStatus(status.getStatus());
        return statusDto;
    }

    public RequirementStatusDto saveNewRequirementStatus(RequirementStatus requirementStatus){
        var rStatus = this.getRepository().save(requirementStatus);
        RequirementStatusDto statusDto = new RequirementStatusDto();
        statusDto.set_id(requirementStatus.getId().toString());
        statusDto.setStatus(requirementStatus.getStatus());
        return statusDto;
    }

    public List<RequirementStatusDto> getAllRequirementStatus(){
        List<RequirementStatusDto> returnList = new ArrayList<>();
        Iterable<Document> requirementsStatus = this.commonResourcesRepository.getAll(Database.requirementStatusCollection);
        requirementsStatus.forEach(requirementStatus -> {
            RequirementStatusDto rStatus = new RequirementStatusDto();
            var value = requirementStatus.getObjectId("_id");
            var status = requirementStatus.get("status");
            rStatus.set_id(value.toString());
            rStatus.setStatus(status.toString());
            returnList.add(rStatus);
        });
        return returnList;
    }


    @Override
    public CrudRepository<RequirementStatus, ObjectId> getRepository() {
        return requirementStatusRepository;
    }
}
