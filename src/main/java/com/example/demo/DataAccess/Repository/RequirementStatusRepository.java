package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Models.RequirementStatus;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface RequirementStatusRepository extends MongoRepository<RequirementStatus, ObjectId> {
}
