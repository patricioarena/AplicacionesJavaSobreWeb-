package com.example.demo.DataAccess.DbMongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface IDbMongoConfiguration {

    MongoClient myClient();
    MongoDatabase dbContext();
}
