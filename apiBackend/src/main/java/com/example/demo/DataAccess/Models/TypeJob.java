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

    private ObjectId id;
    private String jobName;
    private boolean deleted;

    public TypeJob() {
    }

    public TypeJob(String jobName, ObjectId id, boolean deleted) {
        super();

        this.id = id;
        this.jobName = jobName;
        this.deleted = deleted;
    }

}
