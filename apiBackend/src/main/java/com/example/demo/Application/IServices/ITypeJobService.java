package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.TypeJob;
import com.example.demo.Domain.TypeJobDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ITypeJobService extends IGenericService<TypeJob, ObjectId>{
    List<TypeJobDto> getAllJobs();
    ObjectId getObjectIdTypeJob(TypeJob model);
}
