package com.example.apifrontend.services;

public class NotAuthorizationHeaderException extends Exception {
    public NotAuthorizationHeaderException() {
        super("No Authorization header found in request");
    }
}
