package com.example.demo.Controllers;

import com.example.demo.DataAccess.DbFirebase.CloudFirestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Tenga en cuenta que @RestController se usa para escribir la interfaz
 * @Controller se usa para escribir saltos de página
 */

@RestController
//@Controller
@SpringBootApplication
@RequestMapping("/user")
public class UserController {

    @Resource
    CloudFirestore cloudFirestore;

//    public UserController(CloudFirestore cloudFirestore) {
//        this.cloudFirestore = cloudFirestore;
//    }

    /**
     * Si ResponseBody devuelve un objeto, el resultado devuelto se convertirá al formato Json para su salida
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public Map<String, Object> getUser(@PathVariable String id) throws ExecutionException, InterruptedException {

        DocumentReference docRef = this.cloudFirestore.context().collection("usuarios").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            var data =  document.getData();
            System.out.println("Document data: " + data);
            return data;
        } else {
            System.out.println("No such document!");
            return null;
        }
//        return User.findById(id);
    }

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public User getUser(@RequestParam(value = "idUser", defaultValue = "1") int _idUser) {
//        return User.getById(idUser);
//    }
}
