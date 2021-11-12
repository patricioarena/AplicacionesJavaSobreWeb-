package com.example.apiFrontend.services;

public class NotAuthorizationHeaderException extends Exception {
    public NotAuthorizationHeaderException() {
        super("No Authorization header found in request");
    }
}
