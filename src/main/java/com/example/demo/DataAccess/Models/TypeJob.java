package com.example.demo.DataAccess.Models;

import com.example.demo.DataAccess.Database;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Getter
@Setter
@Document(collection = Database.typeJobCollection)
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
