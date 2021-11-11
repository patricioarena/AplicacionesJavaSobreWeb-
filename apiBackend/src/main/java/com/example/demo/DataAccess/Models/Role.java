package com.example.demo.DataAccess.Models;

import com.example.demo.DataAccess.Database;
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
@Document(collection = Database.roleCollection)
public class Role {

    private ObjectId _id;
    private String roleName;
    private boolean deleted;

    public Role() {
    }

    public Role(ObjectId id, String roleName) {
        super();

        this._id = id;
        this.roleName = roleName;
        this.deleted = false;
    }
}
