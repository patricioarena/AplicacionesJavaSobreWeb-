package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;


@Repository
@Configuration
public class CommonResourcesRepository<T> {

    private final IDbMongoConfiguration dbMongoConfiguration;
    private MongoCollection<Document> collectionReference;

    @Autowired(required = false)
    public CommonResourcesRepository(IDbMongoConfiguration dbMongoConfiguration) {
        this.dbMongoConfiguration = dbMongoConfiguration;
    }

    public void initialized(String collectionName){
        this.collectionReference = this.dbMongoConfiguration.dbContext().getCollection(collectionName);
    }

    public Iterable<Document> getAll() {
        var iterableDocs = collectionReference.find();
        return iterableDocs;
    }


    public int setDeleted(String id){
        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("deleted",true);

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(id));

        var updateResult = collectionReference.updateOne(searchQuery, setQuery);

        if(updateResult.getMatchedCount() == 1 && updateResult.getModifiedCount() == 1){
            return 1;
        }else {
            return 0;
        }

    }
}
