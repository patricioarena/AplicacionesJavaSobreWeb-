package com.example.demo.Application.Services;


import com.example.demo.Application.IServices.IRequirementService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.Requirement;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.RequirementRepository;
import com.example.demo.Domain.RequirementDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Configuration
public class RequirementService extends GenericService<Requirement, ObjectId> implements IRequirementService {

    private final RequirementRepository requirementRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository, CommonResourcesRepository commonResourcesRepository) {
        this.requirementRepository = requirementRepository;
        this.commonResourcesRepository = commonResourcesRepository;
    }

    private RequirementDto getRequirementDto(Requirement requirement) {
        RequirementDto requirementDto = new RequirementDto();

        requirementDto.set_id(requirement.get_id().toString());
        requirementDto.set_idRequestPerson(requirement.get_idRequestPerson().toString());
        requirementDto.set_idTypeJob(requirement.get_idTypeJob().toString());
        requirementDto.set_idRequirementStatus(requirement.get_idRequirementStatus().toString());
        requirementDto.setDate(requirement.getDate());
        requirementDto.setDescription(requirement.getDescription());
        requirementDto.setZone(requirement.getZone());
        requirementDto.setDeleted(requirement.isDeleted());

        return requirementDto;
    }

    public RequirementDto getRequirementById(ObjectId id){
        var requirement = super.get(id);

        return getRequirementDto(requirement);
    }

    public RequirementDto saveNewRequirement(Requirement requirement){
        var requirementSave = getRepository().save(requirement);

        return getRequirementDto(requirement);
    }

    public List<RequirementDto> getAllRequirements(){
        List<RequirementDto> returnList = new ArrayList<>();
        Iterable<Document> requirements = commonResourcesRepository.getAll(Database.requirementCollection);

        requirements.forEach(requirement -> {
            RequirementDto requirementDto = new RequirementDto();
            var value = requirement.getObjectId("_id");
            var valueRequestPerson = requirement.getObjectId("_idRequestPerson");
            var valueTypeJob = requirement.getObjectId("_idTypeJob");
            var valueRequirementStatus = requirement.getObjectId("_idRequirementStatus");
            var date = requirement.get("date");
            var description = requirement.get("description");
            var zone = requirement.get("zone");
            var deleted = requirement.get("deleted");
            requirementDto.set_id(value.toString());
            requirementDto.set_idRequestPerson(valueRequestPerson.toString());
            requirementDto.set_idTypeJob(valueTypeJob.toString());
            requirementDto.set_idRequirementStatus(valueRequirementStatus.toString());
            requirementDto.setDate((Date) date);
            requirementDto.setDescription(description.toString());
            requirementDto.setZone(zone.toString());
            requirementDto.setDeleted((Boolean) deleted);
            returnList.add(requirementDto);
        });
        return returnList;
    }

    public RequirementDto deleted(String id){
        RequirementDto requirementDto = new RequirementDto();
        var result = commonResourcesRepository.setDeleted(id,Database.requirementCollection);

        if (result == 1){
            Optional<Requirement> requirementOptional = getRepository().findById(new ObjectId(id));
            Requirement requirement = requirementOptional.get();
            requirementDto.set_id(id);
            requirementDto.set_idRequestPerson(requirement.get_idRequestPerson().toString());
            requirementDto.set_idTypeJob(requirement.get_idTypeJob().toString());
            requirementDto.set_idRequirementStatus(requirement.get_idRequirementStatus().toString());
            requirementDto.setDate(requirement.getDate());
            requirementDto.setDescription(requirement.getDescription());
            requirementDto.setZone(requirement.getZone());
            requirementDto.setDeleted(requirement.isDeleted());

            return requirementDto;
        }else{
            return requirementDto;
        }
    }

    public RequirementDto modifyRequirement(RequirementDto model){
        Optional<Requirement> requirementOptional = getRepository().findById(new ObjectId(model.get_id()));
        Requirement requirement = requirementOptional.get();
        requirement.set_id(new ObjectId(model.get_id()));
        requirement.set_idRequestPerson(new ObjectId(model.get_idRequestPerson()));
        requirement.set_idTypeJob(new ObjectId(model.get_idTypeJob()));
        requirement.set_idRequirementStatus(new ObjectId(model.get_idRequirementStatus()));
        requirement.setDate(model.getDate());
        requirement.setDescription(model.getDescription());
        requirement.setZone(model.getZone());
        requirement.setDeleted(model.isDeleted());
        if (!model.isDeleted()){
            requirement.setDeleted(false);
        }else{
            requirement.setDeleted(true);
        }

        getRepository().save(requirement);

        RequirementDto requirementDto = new RequirementDto();
        requirementDto.set_id(model.get_id());
        requirementDto.set_idRequestPerson(model.get_idRequestPerson());
        requirementDto.set_idTypeJob(model.get_idTypeJob());
        requirementDto.set_idRequirementStatus(model.get_idRequirementStatus());
        requirementDto.setDate(model.getDate());
        requirementDto.setDescription(model.getDescription());
        requirementDto.setZone(model.getZone());
        requirementDto.setDeleted(model.isDeleted());
        if (!model.isDeleted()){
            requirement.setDeleted(false);
        }else{
            requirement.setDeleted(true);
        }

        return requirementDto;
    }

    @Override
    public CrudRepository<Requirement, ObjectId> getRepository() {
        return requirementRepository;
    }
}
