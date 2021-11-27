package com.example.demo.Application.Services;


import com.example.demo.Application.IServices.IRequirementService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.Address;
import com.example.demo.DataAccess.Models.Requirement;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.RequirementRepository;
import com.example.demo.Domain.RequirementDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// TODO: 25/10/2021 Ver lo que se pueda cambiar por mapper

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
        requirementDto.setAddress(requirement.getAddress());
        requirementDto.setDeleted(requirement.isDeleted());
        return requirementDto;
    }

    public RequirementDto getRequirementById(String id){
        var requirement = super.get(new ObjectId(id));
        return getRequirementDto(requirement);
    }

    public RequirementDto saveNewRequirement(RequirementDto requirementDto){
        ModelMapper modelMapper = new ModelMapper();

        Requirement requirement = modelMapper.map(requirementDto, Requirement.class);
        requirement.set_idRequestPerson(new ObjectId(requirementDto.get_idRequestPerson()));
        requirement.set_idRequirementStatus(new ObjectId(requirementDto.get_idRequirementStatus()));
        requirement.set_idTypeJob( new ObjectId(requirementDto.get_idTypeJob()));

        var requirementSave = this.getRepository().save(requirement);
        return getRequirementDto(requirementSave);
    }

    public List<RequirementDto> getAllRequirements(){
        List<RequirementDto> returnList = new ArrayList<>();
        Iterable<Document> requirements = this.commonResourcesRepository.getAll(Database.requirementCollection);
        this.MapRequirementToRequirementDto(returnList, requirements);
        return returnList;
    }

    public RequirementDto deleted(String id){
        RequirementDto requirementDto = new RequirementDto();
        var result = this.commonResourcesRepository.deleted(id,Database.requirementCollection);
        if (result == 1){
            Optional<Requirement> requirementOptional = this.getRepository().findById(new ObjectId(id));
            Requirement requirement = requirementOptional.get();
            requirementDto.set_id(id);
            requirementDto.set_idRequestPerson(requirement.get_idRequestPerson().toString());
            requirementDto.set_idTypeJob(requirement.get_idTypeJob().toString());
            requirementDto.set_idRequirementStatus(requirement.get_idRequirementStatus().toString());
            requirementDto.setDate(requirement.getDate());
            requirementDto.setDescription(requirement.getDescription());
            requirementDto.setZone(requirement.getZone());
            requirementDto.setAddress(requirement.getAddress());
            requirementDto.setDeleted(requirement.isDeleted());
            return requirementDto;
        }else{
            return requirementDto;
        }
    }

    public RequirementDto modifyRequirement(RequirementDto model){
        Optional<Requirement> requirementOptional = this.getRepository().findById(new ObjectId(model.get_id()));
        Requirement requirement = requirementOptional.get();
        requirement.set_id(new ObjectId(model.get_id()));
        requirement.set_idRequestPerson(new ObjectId(model.get_idRequestPerson()));
        requirement.set_idTypeJob(new ObjectId(model.get_idTypeJob()));
        requirement.set_idRequirementStatus(new ObjectId(model.get_idRequirementStatus()));
        requirement.setDate(model.getDate());
        requirement.setDescription(model.getDescription());
        requirement.setZone(model.getZone());
        requirement.setAddress(model.getAddress());
        requirement.setDeleted(model.isDeleted());

        if (!model.isDeleted()){
            requirement.setDeleted(false);
        }else{
            requirement.setDeleted(true);
        }

        this.getRepository().save(requirement);
        RequirementDto requirementDto = new RequirementDto();
        requirementDto.set_id(model.get_id());
        requirementDto.set_idRequestPerson(model.get_idRequestPerson());
        requirementDto.set_idTypeJob(model.get_idTypeJob());
        requirementDto.set_idRequirementStatus(model.get_idRequirementStatus());
        requirementDto.setDate(model.getDate());
        requirementDto.setDescription(model.getDescription());
        requirementDto.setZone(model.getZone());
        requirementDto.setAddress(model.getAddress());
        requirementDto.setDeleted(model.isDeleted());

        if (!model.isDeleted()){
            requirement.setDeleted(false);
        }else{
            requirement.setDeleted(true);
        }

        return requirementDto;
    }

    public List<RequirementDto> getAllRequirementsByRequestPerson(String idRequestPerson) {
        List<RequirementDto> returnList = new ArrayList<>();
        Iterable<Document> requirements = this.commonResourcesRepository.getAllBySomeId(Database.requirementCollection,"_idRequestPerson", idRequestPerson);
        this.MapRequirementToRequirementDto(returnList, requirements);
        return returnList;
    }

    private void MapRequirementToRequirementDto(List<RequirementDto> returnList, Iterable<Document> requirements) {

        ModelMapper modelMapper = new ModelMapper();


        requirements.forEach(requirement -> {
            RequirementDto requirementDto = new RequirementDto();
            var value = requirement.getObjectId("_id");
            var valueRequestPerson = requirement.getObjectId("_idRequestPerson");
            var valueTypeJob = requirement.getObjectId("_idTypeJob");
            var valueRequirementStatus = requirement.getObjectId("_idRequirementStatus");
            var date = requirement.get("date");
            var description = requirement.get("description");
            var addressString = requirement.get("address");
            var zone = requirement.get("zone");

            var address = modelMapper.map(addressString, Address.class);

            var deleted = requirement.get("deleted");
            requirementDto.set_id(value.toString());
            requirementDto.set_idRequestPerson(valueRequestPerson.toString());
            requirementDto.set_idTypeJob(valueTypeJob.toString());
            requirementDto.set_idRequirementStatus(valueRequirementStatus.toString());
            requirementDto.setDate((Date) date);
            requirementDto.setDescription(description.toString());
            requirementDto.setZone(zone.toString());

            requirementDto.setAddress(address);

            requirementDto.setDeleted((Boolean) deleted);
            returnList.add(requirementDto);
        });
    }

    @Override
    public CrudRepository<Requirement, ObjectId> getRepository() {
        return requirementRepository;
    }
}
