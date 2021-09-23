package com.example.demo.controllers;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Tenga en cuenta que @RestController se usa para escribir la interfaz
 * @Controller se usa para escribir saltos de página
 */

@RestController
//@Controller
@SpringBootApplication
@RequestMapping("/user")
public class UserController {
    /**
     * Si ResponseBody devuelve un objeto, el resultado devuelto se convertirá al formato Json para su salida
     *
     * @param _idUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUser/{_id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int _idUser) {

        return UserDAO.getById(_idUser);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(@RequestParam(value = "_idUser", defaultValue = "1") int _idUser) {
        return UserDAO.getById(_idUser);
    }
}
