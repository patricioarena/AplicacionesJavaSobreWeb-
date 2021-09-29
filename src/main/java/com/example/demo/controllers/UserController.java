package com.example.demo.controllers;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

/**
 * Tenga en cuenta que @RestController se usa para escribir la interfaz
 *
 * @Controller se usa para escribir saltos de página
 * @CrossOrigin se usa para no tener problemas con Cors
 */

//@CrossOrigin(origins = "Localhost:8080")
@RestController
//@Controller
@RequestMapping("/user")
public class UserController {

    private final IDbMongoConfiguration dbMongoConfiguration;

    @Autowired
    public UserController(IDbMongoConfiguration dbMongoConfiguration) {
        this.dbMongoConfiguration = dbMongoConfiguration;
    }

    /**
     * Si ResponseBody devuelve un objeto, el resultado devuelto se convertirá al formato Json para su salida
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) throws ExecutionException, InterruptedException {

        MongoDatabase database = dbMongoConfiguration.dbContext();

        var collection = database.getCollection("usuarios");

        Document aDoc = new Document().append("hola2", "mundo2");
        collection.insertOne(aDoc);


//        DocumentReference docRef = this.cloudFirestore.context().collection("usuarios").document(id);
//        ApiFuture<DocumentSnapshot> future = docRef.get();
//        DocumentSnapshot document = future.get();
//        if (document.exists()) {
//            var data =  document.getData();
//            System.out.println("Document data: " + data);
//            return data;
//        } else {
//            System.out.println("No such document!");
        return "null";
//        }
//        return User.findById(id);
    }

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public User getUser(@RequestParam(value = "idUser", defaultValue = "1") int _idUser) {
//        return User.getById(idUser);
//    }
}
