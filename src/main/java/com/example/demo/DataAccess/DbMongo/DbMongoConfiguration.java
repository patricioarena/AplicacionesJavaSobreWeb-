package com.example.demo.DataAccess.DbMongo;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

@Service
@Configuration
public class DbMongoConfiguration implements IDbMongoConfiguration{

    public DbMongoConfiguration() {
    }

    private ConnectionString connectionString;
    private String dbName;

    private ConnectionString getConnectionString() {
        return connectionString;
    }

    private void setConnectionString(String connectionString) {
        this.connectionString = new ConnectionString(connectionString);
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @PostConstruct
    private void Initialize() throws FileNotFoundException {
        try {

            String path = System.getProperty("user.dir") + "\\appsettings.json";

            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();
            HashMap<String, String> json = gson.fromJson(bufferedReader, HashMap.class);

            String aux = json.get("connectionString");
            String db = json.get("db");

            aux = aux.replaceFirst("udb", db);
            aux = aux.replaceFirst("ucluster", json.get("cluster"));
            aux = aux.replaceFirst("uroot", json.get("user"));
            aux = aux.replaceFirst("upass", json.get("pass"));

            setConnectionString(aux);
            setDbName(db);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MongoClient myClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(getConnectionString())
            .build();

        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    public MongoDatabase dbContext() {
        MongoDatabase database = myClient().getDatabase(getDbName());
        return database;
    }


}
