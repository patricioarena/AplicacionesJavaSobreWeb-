package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TypeJob {
    private String _id;
    private String jobName;
    private boolean deleted;
}
