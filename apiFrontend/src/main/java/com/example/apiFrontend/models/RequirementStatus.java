package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequirementStatus {

    private String _id;
    private String status;

    public RequirementStatus() {
    }

    public RequirementStatus(String _id, String status) {
        super();

        this._id = _id;
        this.status = status;
    }
}
