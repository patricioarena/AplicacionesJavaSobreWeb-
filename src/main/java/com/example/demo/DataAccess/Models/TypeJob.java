package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Data
@Getter
@Setter
public class TypeJob {
    private ObjectId _idTypeJob;
    private String jobName;
}
