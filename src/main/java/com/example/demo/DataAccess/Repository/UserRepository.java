package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.example.demo.DataAccess.Models.AbstractUser;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Service;

@Service
public class UserRepository extends  AbstractMongoRepository<AbstractUser>{

    @Autowired
    protected UserRepository (IDbMongoConfiguration dbMongoConfiguration){
        super(dbMongoConfiguration, "usuarios");
    }
}
