package com.example.demo.DataAccess.Models;

import com.example.demo.DataAccess.Database;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = Database.requirementStatusCollection)
public class RequirementStatus {

    private ObjectId id;
    private String status;

    public RequirementStatus() {
    }

    public RequirementStatus(ObjectId id, String status) {
        super();

        this.id = id;
        this.status = status;
    }
}
