package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Models.Requirement;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface RequirementRepository extends MongoRepository <Requirement, ObjectId> {
}
