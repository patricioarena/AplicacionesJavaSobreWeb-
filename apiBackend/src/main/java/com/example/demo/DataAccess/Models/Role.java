package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = "roleCollection")
public class Role {

    private String roleName;

    public Role() {
    }

    public Role(String roleName) {
        super();

        this.roleName = roleName;
    }
}
