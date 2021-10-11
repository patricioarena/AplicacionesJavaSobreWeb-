package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.example.demo.DataAccess.Models.Requirement;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import java.lang.reflect.ParameterizedType;


@Repository
@Configuration
public class CommonResourcesRepository<T> {

    private final IDbMongoConfiguration dbMongoConfiguration;
    private MongoCollection<Document> collectionReference;
    private Class<T> parameterizedType;


    @Autowired(required = false)
    public CommonResourcesRepository(IDbMongoConfiguration dbMongoConfiguration) {
        this.dbMongoConfiguration = dbMongoConfiguration;
    }

    public void initialized(String collectionName){
        this.collectionReference = this.dbMongoConfiguration.dbContext().getCollection(collectionName);
        this.parameterizedType = getParameterizedType();
    }

    //region PRIVATE METHODS
    private Class<T> getParameterizedType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    public Iterable<Document> getAll() {
        var iterableDocs = collectionReference.find();
        return iterableDocs;
    }
}
