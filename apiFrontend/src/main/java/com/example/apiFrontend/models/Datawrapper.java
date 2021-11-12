package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Datawrapper<T> {
    public String message;
    public T data;

    public Datawrapper(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public Datawrapper(T data) {
        this.data = data;
    }
}
