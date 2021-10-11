package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Getter
@Setter
@Document(collection = "typeJobCollection")
public class TypeJob {

    private String jobName;

    public TypeJob() {
    }

    public TypeJob(String jobName, ObjectId id) {
        super();

        this.jobName = jobName;
    }

}