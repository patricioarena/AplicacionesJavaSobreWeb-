package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.ITypeJobService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.TypeJob;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.TypeJobRepository;
import com.example.demo.Domain.TypeJobDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// TODO: 25/10/2021 Ver lo que se pueda cambiar por mapper

@Service
@Configuration
public class TypeJobService extends GenericService<TypeJob, ObjectId> implements ITypeJobService {

    private final TypeJobRepository typeJobRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    @Autowired
    public TypeJobService(TypeJobRepository typeJobRepository, CommonResourcesRepository commonResourcesRepository) {
        this.typeJobRepository = typeJobRepository;
        this.commonResourcesRepository = commonResourcesRepository;
    }

    private Document getDocument(TypeJob model) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(model);
        Document document = Document.parse(json);
        return document;
    }

    //metodo que devuelva un objectId pasandole un modelo de TypeJob
    public ObjectId getObjectIdTypeJob(TypeJob model) {
        Document document = getDocument(model);
        ObjectId id = document.getObjectId("_id");
        return id;
    }

    public TypeJobDto getJobById(ObjectId id) {
        var job = super.get(id);
        TypeJobDto jobDto = new TypeJobDto();
        jobDto.set_id(job.getId().toString());
        jobDto.setJobName(job.getJobName());
        jobDto.setDeleted(job.isDeleted());
        return jobDto;
    }

    public TypeJobDto saveNewTypeJob(TypeJob typeJob) {
        var job = this.getRepository().save(typeJob);
        TypeJobDto typeJobDto = new TypeJobDto();
        typeJobDto.set_id(typeJob.getId().toString());
        typeJobDto.setJobName(job.getJobName());
        typeJobDto.setDeleted(job.isDeleted());
        return typeJobDto;
    }

    //Extraer logica a un metodo
    public List<TypeJobDto> getAllJobs() {
        List<TypeJobDto> returnList = new ArrayList<>();
        Iterable<Document> typeJobs = this.commonResourcesRepository.getAll(Database.typeJobCollection); // usar otro metodo o query para traer todos los documentos
        typeJobs.forEach(typeJob -> {
            TypeJobDto job = new TypeJobDto();
            var value = typeJob.getObjectId("_id");
            var name = typeJob.get("jobName");
            var deleted = typeJob.get("deleted");
            job.set_id(value.toString());
            job.setJobName(name.toString());
            job.setDeleted((Boolean) deleted);
            // System.out.println("" + typeJob);
            returnList.add(job);
        });
        return returnList;
    }

    public TypeJobDto deleted(String id) {
        TypeJobDto typeJobDto = new TypeJobDto();
        var result = this.commonResourcesRepository.deleted(id, Database.typeJobCollection);
        if (result == 1) {
            Optional<TypeJob> typeOptional = this.getRepository().findById(new ObjectId(id));
            TypeJob typeJob = typeOptional.get();
            typeJobDto.set_id(id);
            typeJobDto.setJobName(typeJob.getJobName());
            return typeJobDto;
        } else {
            return typeJobDto;
        }
    }

    public TypeJobDto modifyJob(TypeJobDto model) {
        Optional<TypeJob> typeJobOptional = this.getRepository().findById(new ObjectId(model.get_id()));
        TypeJob typeJob = typeJobOptional.get();
        typeJob.setId((new ObjectId(model.get_id())));
        typeJob.setJobName(model.getJobName());

        if (!model.isDeleted()) {
            typeJob.setDeleted(false);
        } else {
            typeJob.setDeleted(true);
        }

        this.getRepository().save(typeJob);
        TypeJobDto typeJobDto = new TypeJobDto();
        typeJobDto.set_id(model.get_id());
        typeJobDto.setJobName(model.getJobName());

        if (!model.isDeleted()) {
            typeJobDto.setDeleted(false);
        } else {
            typeJobDto.setDeleted(true);
        }

        return typeJobDto;
    }

    @Override
    public CrudRepository<TypeJob, ObjectId> getRepository() {
        return typeJobRepository;
    }
}
