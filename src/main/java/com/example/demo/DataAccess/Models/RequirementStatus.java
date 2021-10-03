package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Data
@Getter
@Setter
public class RequirementStatus {
    private ObjectId _idRequirementStatus;
    private String status;
}
