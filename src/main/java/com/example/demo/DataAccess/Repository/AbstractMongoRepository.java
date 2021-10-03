package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.lang.Nullable;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.Object;
import java.util.*;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractMongoRepository<T> {
    private final MongoCollection<Document> collectionReference;
    private final IDbMongoConfiguration dbMongoConfiguration;
    private final String collectionName;
    private final Class<T> parameterizedType;

    protected AbstractMongoRepository(IDbMongoConfiguration dbMongoConfiguration, String collection) {
        this.dbMongoConfiguration = dbMongoConfiguration;
        this.collectionReference = this.dbMongoConfiguration.dbContext().getCollection(collection);
        this.collectionName = collection;
        this.parameterizedType = getParameterizedType();
    }

    //region PRIVATE METHODS
    private Class<T> getParameterizedType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    private Object getKeyFromFields(Class<?> clazz, Object t) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(DocumentId.class))
                .findFirst()
                .map(field -> getValue(t, field))
                .orElse(null);
    }

    @Nullable
    private Object getValue(Object t, java.lang.reflect.Field field) {
        field.setAccessible(true);
        try {
            return field.get(t);
        } catch (IllegalAccessException e) {
            System.out.println("Error in getting documentId key" + e.toString());
        }
        return null;
    }

    private Document getDocument(T model) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(model);
        Document document = Document.parse(json);
        return document;
    }

    private List<T> getTs(FindIterable<Document> iterableDocs) {
        List<T> returnList = new ArrayList<>();
        iterableDocs.forEach(doc -> {
            returnList.add((T) doc);
        });
        return returnList;
    }
    //endregion
    //region PROTECTED METHODS
    protected String getDocumentId(T id) {
        Object key;
        Class clzz = id.getClass();
        do {
            key = getKeyFromFields(clzz, id);
            clzz = clzz.getSuperclass();
        } while (key == null && clzz != null);

        if (key == null) {
            return UUID.randomUUID().toString();
        }
        return String.valueOf(key);
    }

    protected MongoCollection<Document> getCollectionReference() {
        return this.collectionReference;
    }

    protected Class<T> getType() {
        return this.parameterizedType;
    }
    //endregion

    //       var resultApiFuture = collectionReference.find(new BasicDBObject("_id", documentId)).set("_id", model.getRoleName());

    public String save(T model) {
        Document document = getDocument(model);
        var result = collectionReference.insertOne(document);
        return document.getObjectId("_id").toString();
    }

    public List<T> get(String id) {
        var documentId = new ObjectId(id);
        var iterableDocs = collectionReference.find(new BasicDBObject("_id", documentId));
        return getTs(iterableDocs);
    }

    public List<T> getAll() {
        var iterableDocs = collectionReference.find();
        return getTs(iterableDocs);
    }

    public void fisicalDelete(String id) {
        var documentId = new ObjectId(id);
        var result = collectionReference.deleteOne(new BasicDBObject("_id", documentId));
    }

    public void fisicalDelete(T model) {
        String documentId = getDocumentId(model);
        var result = collectionReference.deleteOne(new BasicDBObject("_id", documentId));
    }

    public void update(T model) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

}
