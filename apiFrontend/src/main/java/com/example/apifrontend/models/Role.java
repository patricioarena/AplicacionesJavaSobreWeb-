package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Role {
    private String _id;
    private String roleName;
    private boolean deleted;
}
