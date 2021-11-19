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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://programmerclick.com/article/92712109938/

@Service
public class HttpClientAsynchronous {

    private String baseUrl;

    public HttpClientAsynchronous() {
        this.baseUrl = System.getenv("baseUrl");
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
        controlContainsBearer(headers, false); //parametro de activacion
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

    //generar headers ya que no los obtiene, para pasarselos al get post put ...
    private Map<String, String> createHeaders() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("user-agent","PostmanRuntime/7.28.4");
        headersMap.put("accept","*/*");
        headersMap.put("accept-encoding","gzip, deflate, br");
        headersMap.put("content-type","application/json");
        return headersMap;
    }

    public JSONObject GET(String uri) throws Exception {
        Map<String, String> headersMap = createHeaders();
        return this.GET(headersMap, uri);
    }

    public JSONObject POST(String uri, Object data) throws Exception {
        Map<String, String> headersMap = createHeaders();
        return this.POST(headersMap, uri,data);
    }

    public JSONObject PUT(String uri, Object data) throws Exception {
        Map<String, String> headersMap = createHeaders();
        return this.PUT(headersMap, uri, data);
    }

    public JSONObject DELETE(String uri) throws Exception {
        Map<String, String> headersMap = createHeaders();
        return this.DELETE(headersMap, uri);
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
                .uri(URI.create(baseUrl+uri))
                .build();

        return executeRequest(request);
    }

    public JSONObject DELETE(Map<String, String> headers, String uri) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        setHeaders(headers, requestBuilder);
        HttpRequest request = requestBuilder.DELETE().uri(URI.create(baseUrl+uri)).build();
        return executeRequest(request);
    }
}
