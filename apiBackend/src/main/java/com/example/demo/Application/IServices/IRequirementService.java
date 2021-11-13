package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.Requirement;
import com.example.demo.Domain.RequirementDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IRequirementService extends IGenericService<Requirement, ObjectId>{

    RequirementDto getRequirementById(String id);
//    RequirementDto saveNewRequirement(Requirement requirement);
    RequirementDto saveNewRequirement(RequirementDto requirementDto);
    List<RequirementDto> getAllRequirements();
    RequirementDto deleted(String id);
    RequirementDto modifyRequirement(RequirementDto model);
}
