package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.ITypeJobService;
import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.example.demo.DataAccess.Models.TypeJob;
import com.example.demo.DataAccess.Repository.TypeJobRepository;
import com.example.demo.Domain.TypeJobDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
public class TypeJobService extends GenericService<TypeJob, ObjectId> implements ITypeJobService {

    private TypeJobRepository typeJobRepository;
    private final MongoCollection<Document> collectionReference;
    private final IDbMongoConfiguration dbMongoConfiguration;
    private final String collectionName = "typeJobCollection";

    @Autowired
    public TypeJobService(TypeJobRepository typeJobRepository, IDbMongoConfiguration dbMongoConfiguration){
        this.typeJobRepository = typeJobRepository;
        this.dbMongoConfiguration = dbMongoConfiguration;
        this.collectionReference = this.dbMongoConfiguration.dbContext().getCollection(this.collectionName);
    }


    private Document getDocument(TypeJob model) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(model);
        Document document = Document.parse(json);
        return document;
    }

    //metodo que devuelva un objectId pasandole un modelo de TypeJob
    public ObjectId getObjectIdTypeJob(TypeJob model){
        Document document = getDocument(model);
        return document.getObjectId("_id");
    }


    //Extraer logica a un metodo
    public List<TypeJobDto> getAllJobs() {
        List<TypeJobDto> returnList = new ArrayList<>();
        Iterable<Document> typeJobs = collectionReference.find(); // usar otro metodo o query para traer todos los documentos
        TypeJobDto job = new TypeJobDto();

        typeJobs.forEach(typeJob -> {
            var value = typeJob.getObjectId("_id");
            var name = typeJob.get("jobName");
            job.set_id(value.toString());
            job.setJobName(name.toString());
            System.out.println("" + typeJob);
        });
        returnList.add(job);
        System.out.println(job);
        return returnList;
    }

    @Override
    public CrudRepository<TypeJob, ObjectId> getRepository() {
        return typeJobRepository;
    }
}
