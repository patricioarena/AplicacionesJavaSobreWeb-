package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.example.demo.Domain.UserDto;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
@Configuration
public class CommonResourcesRepository<T> {

    private final IDbMongoConfiguration dbMongoConfiguration;
    private MongoDatabase dbMongo = null;

    @Autowired(required = false)
    public CommonResourcesRepository(IDbMongoConfiguration dbMongoConfiguration) {
        this.dbMongoConfiguration = dbMongoConfiguration;
        this.dbMongo = this.dbMongoConfiguration.dbContext();
    }

    private MongoCollection<Document> getDocumentMongoCollection(String collectionName) {
        return this.dbMongo.getCollection(collectionName);
    }

    public Iterable<Document> getAll(String collectionName) {
        var iterableDocs = getDocumentMongoCollection(collectionName).find();
        return iterableDocs;
    }

    public Integer deleted(String id, String collectionName) {
        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("deleted", true);

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(id));

        var updateResult = getDocumentMongoCollection(collectionName).updateOne(searchQuery, setQuery);

        if (updateResult.getMatchedCount() == 1 && updateResult.getModifiedCount() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public Integer updateFields(T dto, String collectionName) {
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        BasicDBObject updateFields = BasicDBObject.parse(json);

        ArrayList<ObjectId> roles = new ArrayList<>();

        if (updateFields.containsKey("roles")) {
            var s1 = updateFields.getString("roles");
            var s2 = s1.replaceAll("^\\[|]$", "");
            var arrString = s2.replaceAll(", ", ",");
            List<String> myList = Arrays.asList(arrString.split(","));
            myList.forEach(role -> roles.add(new ObjectId(role)));
            updateFields.removeField("roles");
            updateFields.append("roles", roles);
        }

        String _id = updateFields.getString("_id");
        updateFields.removeField("_id");

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(_id));
        var updateResult = getDocumentMongoCollection(collectionName).updateOne(searchQuery, setQuery);

        if (updateResult.getMatchedCount() == 1 && updateResult.getModifiedCount() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

}
