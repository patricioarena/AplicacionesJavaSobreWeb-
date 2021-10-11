package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = "requirementStatusCollection")
public class RequirementStatus {

    private String status;

    public RequirementStatus() {
    }

    public RequirementStatus(String status) {
        super();

        this.status = status;
    }
}
