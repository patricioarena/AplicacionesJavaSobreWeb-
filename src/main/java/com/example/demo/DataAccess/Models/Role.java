package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "role")
public class Role {
    @Id
    private int _idRole;
    private String roleName;
}
