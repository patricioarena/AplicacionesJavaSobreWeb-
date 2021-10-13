package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IAbstractUserService;
import com.example.demo.DataAccess.Models.AbstractUser;
import com.example.demo.DataAccess.Models.AdminPerson;
import com.example.demo.DataAccess.Repository.AbstractUserRepository;
import com.example.demo.Domain.AbstractUserDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Configuration
public class AbstractUserService extends GenericService<AbstractUser, ObjectId> implements IAbstractUserService {

    private AbstractUserRepository abstractUserRepository;

    @Autowired
    public AbstractUserService(AbstractUserRepository abstractUserRepository){
        this.abstractUserRepository = abstractUserRepository;
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

    @Override
    public CrudRepository<AbstractUser, ObjectId> getRepository() {
        return abstractUserRepository;
    }
}
