package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.RequirementStatus;
import com.example.demo.Domain.RequirementStatusDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IRequirementStatusService extends IGenericService<RequirementStatus, ObjectId>{

    RequirementStatusDto getRequirementStatusById(ObjectId id);
    RequirementStatusDto saveNewRequirementStatus(RequirementStatus requirementStatus);
    List<RequirementStatusDto> getAllRequirementStatus();
}
