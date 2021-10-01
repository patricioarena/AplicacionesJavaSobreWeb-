package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.lang.Nullable;
import org.bson.Document;
import java.lang.Object;
import java.lang.annotation.Annotation;
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

    private Class<T> getParameterizedType(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>)type.getActualTypeArguments()[0];
    }

 //       var resultApiFuture = collectionReference.find(new BasicDBObject("_id", documentId)).set("_id", model.getRoleName());

    public boolean save (T model){
        Gson gson = new Gson();
        HashMap<String, String> json = gson.fromJson(model.toString(), HashMap.class);
       // System.out.println(json.toString());

      //  var resultApiFuture = collectionReference.insertOne(document);
        return true;
    }

    public void delete(T model){
        String documentId = getDocumentId(model);
        var resultApiFuture = collectionReference.deleteOne(new BasicDBObject("_id", documentId));
    }

    public List<T> getAll(){
       // return collectionReference.findAll(Role.class);
        var documentsList = collectionReference.find();
        List<T> returnList = new ArrayList<>();
        documentsList.forEach(doc -> {
            returnList.add((T) doc);
        });
      //  if (collectionName.contains("Role")){

      // }
       // documentsList.forEach(doc -> returnList.add(doc));
        return returnList;
    }


   /* public Optional<T> get(String documentId){
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();

        try {
            DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();

            if(documentSnapshot.exists()){
                return Optional.ofNullable(documentSnapshot.toObject(parameterizedType));
            }

        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occurred retrieving: {} {}, {}", collectionName, documentId, e.getMessage());
        }

        return Optional.empty();

    }
*/

    protected String getDocumentId(T t) {
        Object key;
        Class clzz = t.getClass();
        do{
            key = getKeyFromFields(clzz, t);
            clzz = clzz.getSuperclass();
        } while(key == null && clzz != null);

        if(key==null){
            return UUID.randomUUID().toString();
        }
        return String.valueOf(key);
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

   protected MongoCollection<Document> getCollectionReference(){
        return this.collectionReference;
    }
    protected Class<T> getType(){ return this.parameterizedType; }

}
