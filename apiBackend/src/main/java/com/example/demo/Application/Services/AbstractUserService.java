package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IAbstractUserService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.AbstractUser;
import com.example.demo.DataAccess.Models.Address;
import com.example.demo.DataAccess.Models.AdminPerson;
import com.example.demo.DataAccess.Repository.AbstractUserRepository;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.Domain.AbstractUserDto;
import com.example.demo.Domain.RequirementDto;
import com.google.gson.Gson;
import jdk.jshell.spi.ExecutionControl;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Configuration
public class AbstractUserService extends GenericService<AbstractUser, ObjectId> implements IAbstractUserService {

    private AbstractUserRepository abstractUserRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    @Autowired
    public AbstractUserService(AbstractUserRepository abstractUserRepository, CommonResourcesRepository commonResourcesRepository){
        this.abstractUserRepository = abstractUserRepository;
        this.commonResourcesRepository = commonResourcesRepository;
    }

    @Override
    public void setDeleted(AbstractUser user){
        user.setDeleted(true);
        getRepository().save(user);
    }

    @Override
    public void setDeleted(ObjectId id){
        var get = getRepository();
        Optional<AbstractUser> obj = get.findById(id);
        if(obj.isPresent()){
            AbstractUser abstractUser = obj.get();
            abstractUser.setDeleted(true);
            getRepository().save(abstractUser);
        }
    }

    @Override
    public AbstractUser saveNewUser(AbstractUserDto abstractUser) {
        var roleId = new ObjectId(abstractUser.get_idRole());

        AbstractUser user = new AdminPerson();

        user.set_idRole(roleId);
        user.setName(abstractUser.getName());
        user.setLastName(abstractUser.getLastName());
        user.setBirthDate(abstractUser.getBirthDate());
        user.setTypeDoc(abstractUser.getTypeDoc());
        user.setNumberDoc(abstractUser.getNumberDoc());
        user.setGender(abstractUser.getGender());
        user.setEmail(abstractUser.getEmail());
        user.setTelephoneNumber(abstractUser.getTelephoneNumber());
        user.setReputation(abstractUser.getReputation());
        user.setAddress(abstractUser.getAddress());
        user.setDeleted(false);
        return getRepository().save(user);
    }

    public List<AbstractUserDto> getAllUsers(){
        List<AbstractUserDto> returnList = new ArrayList<>();
        Iterable<Document> iterableDocuments = commonResourcesRepository.getAll(Database.userCollection);

        iterableDocuments.forEach(user -> {
            AbstractUserDto userDto = new AbstractUserDto();
            var id = user.getObjectId("_id");
            var idRole = user.getObjectId("_idRole");
            var name = user.get("name");
            var lastName = user.get("lastName");
            var birthDate = user.get("birthDate");
            var typeDoc = user.get("typeDoc");
            var numberDoc = user.get("numberDoc");
            var gender = user.get("gender");
            var email = user.get("email");
            var telephoneNumber = user.get("telephoneNumber");
            var reputation = user.get("reputation");
            var address = user.get("address");
            var deleted = user.get("deleted");

            Gson gson = new Gson();
            Address model = gson.fromJson(((Document) address).toJson(), Address.class);

            userDto.set_id(id.toString());
            userDto.set_idRole(idRole.toString());
            userDto.setName(name.toString());
            userDto.setLastName(lastName.toString());
            userDto.setBirthDate(birthDate.toString());
            userDto.setTypeDoc(typeDoc.toString());
            userDto.setNumberDoc((Integer) numberDoc);
            userDto.setGender(gender.toString());
            userDto.setEmail(email.toString());
            userDto.setTelephoneNumber(telephoneNumber.toString());
            userDto.setReputation(Double.parseDouble(reputation.toString()));
            userDto.setAddress(model);
            userDto.setDeleted((Boolean) deleted);

            returnList.add(userDto);
        });
        return returnList;
    }

    @Override
    public List<AbstractUser> getAll(){
        throw new java.lang.UnsupportedOperationException("Not supported yet. Use getAllUsers");
    }

    @Override
    public CrudRepository<AbstractUser, ObjectId> getRepository() {
        return abstractUserRepository;
    }
}
