//package com.example.demo.controllers;
//
//import com.example.demo.DataAccess.Models.AbstractUser;
//import com.example.demo.DataAccess.Models.OfferPerson;
//import com.example.demo.DataAccess.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
///**
// * Tenga en cuenta que @RestController se usa para escribir la interfaz
// *
// * @Controller se usa para escribir saltos de página
// * @CrossOrigin se usa para no tener problemas con Cors
// */
//
////@CrossOrigin(origins = "Localhost:8080")
//@RestController
////@Controller
//@RequestMapping("/user")
//public class UserController {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    /**
//     * Si ResponseBody devuelve un objeto, el resultado devuelto se convertirá al formato Json para su salida
//     *
//     * @param id
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    public List<AbstractUser> getUser(@PathVariable String id) throws Exception, ExecutionException, InterruptedException {
//        return userRepository.get(id);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
//    public List<AbstractUser> getAll() throws ExecutionException, InterruptedException {
//        return userRepository.getAll();
//    }
//
//    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
//    public String insertUser(@RequestBody OfferPerson model) {
//        return userRepository.save(model);
//    }
//
//    // for develoment use
//    @RequestMapping(value = "/fisicalDelete/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable String id) {
//        userRepository.fisicalDelete(id);
//    }
//
//    // for develoment use
//    @RequestMapping(value = "/fisicalDelete", method = RequestMethod.DELETE)
//    public void delete(@RequestBody OfferPerson model) {
//        userRepository.fisicalDelete(model);
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.DELETE)
//    public void update(@RequestBody OfferPerson model) {
//        userRepository.update(model);
//    }
//
//}
