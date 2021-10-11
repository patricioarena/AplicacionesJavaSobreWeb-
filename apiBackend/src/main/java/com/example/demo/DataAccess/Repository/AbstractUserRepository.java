package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Models.AbstractUser;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Configuration
public interface AbstractUserRepository extends MongoRepository<AbstractUser, ObjectId> {

}
