package com.example.demo.controllers;

import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.example.demo.DataAccess.Models.AbstractUser;
import com.example.demo.DataAccess.Models.OfferPerson;
import com.example.demo.DataAccess.Repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public UserController(IDbMongoConfiguration dbMongoConfiguration, UserRepository userRepository) {
        this.dbMongoConfiguration = dbMongoConfiguration;
        this.userRepository = userRepository;
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

        Document aDoc = new Document().append("hola3", "mundo3");
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

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public int insertUser(@RequestBody OfferPerson model) {
        userRepository.save(model);
        return 1;
    }
}
