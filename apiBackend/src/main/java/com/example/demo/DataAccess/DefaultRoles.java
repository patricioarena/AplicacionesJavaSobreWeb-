    package com.example.demo.DataAccess;

import com.example.demo.DataAccess.Models.Role;
import org.bson.types.ObjectId;
import java.util.Arrays;
import java.util.List;

public final class DefaultRoles {

    private DefaultRoles() { }

    public static final String adminPerson = "6171ffa57831007cb5105356";
    public static final String offerPerson = "6171ffb57831007cb5105357";
    public static final String requestPerson = "6171ffc57831007cb5105358";

    private static Role _adminPerson = new Role(new ObjectId(adminPerson),"adminPerson");
    private static Role _offerPerson = new Role(new ObjectId(offerPerson),"offerPerson");
    private static Role _requestPerson = new Role(new ObjectId(requestPerson),"requestPerson");

    private static List<Role> defaultRolesList = Arrays.asList( _adminPerson, _offerPerson, _requestPerson );

    public static List<Role> getDefaultRolesList() {
        return defaultRolesList;
    }

}
