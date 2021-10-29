package com.example.apiFrontend.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//https://programmerclick.com/article/92712109938/

@Service
public class HttpClientAsynchronous {

    private String baseUrl;

    /*public HttpClientAsynchronous() {
        this.baseUrl = "http://localhost:8080/api/";
    }*/
    public HttpClientAsynchronous() {
        this.baseUrl = "https://0964-186-39-19-206.ngrok.io/api/";
    }


    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static JSONObject getJsonObject(String result) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(result);
    }

    private static void controlContainsBearer(Map<String, String> headers, boolean active) throws NotAuthorizationHeaderException {
        if (active == true){
            if (!headers.containsKey("authorization"))
                throw new NotAuthorizationHeaderException();
        }
    }

    private static void setHeaders(Map<String, String> headers, HttpRequest.Builder output) throws NotAuthorizationHeaderException {
        controlContainsBearer(headers, false);
        headers.forEach((key, value) -> {
            if (key.equals("authorization")) {
                output.header(key, value);
            }
            if (key.equals("content-type")) {
                output.header(key, value);
            }
        });
    }

    private String objectToString(Object data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(data);
    }

    private JSONObject executeRequest(HttpRequest request) throws InterruptedException, ExecutionException, TimeoutException, ParseException {
        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        return getJsonObject(result);
    }

    public JSONObject GET(Map<String, String> headers, String uri) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        setHeaders(headers, requestBuilder);
        HttpRequest request = requestBuilder.GET().uri(URI.create(baseUrl+uri)).build();
        return executeRequest(request);
    }

    public JSONObject POST(Map<String, String> headers, String uri, Object data) throws Exception {

        String requestBody = objectToString(data);

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        setHeaders(headers, requestBuilder);

        HttpRequest request = requestBuilder
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(baseUrl+uri))
                .build();

        return executeRequest(request);
    }

    public JSONObject PUT(Map<String, String> headers, String uri, Object data) throws Exception {

        String requestBody = objectToString(data);

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        setHeaders(headers, requestBuilder);

        HttpRequest request = requestBuilder
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(uri))
                .build();

        return executeRequest(request);
    }

    public JSONObject DELETE(Map<String, String> headers, String uri) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        setHeaders(headers, requestBuilder);
        HttpRequest request = requestBuilder.DELETE().uri(URI.create(uri)).build();
        return executeRequest(request);
    }
}
