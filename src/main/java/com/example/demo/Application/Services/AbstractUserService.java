package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IAbstractUserService;
import com.example.demo.DataAccess.Models.AbstractUser;
import com.example.demo.DataAccess.Repository.AbstractUserRepository;
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
        Optional<AbstractUser> obj = getRepository().findById(id);
        if(obj.isPresent()){
            AbstractUser abstractUser = obj.get();
            abstractUser.setDeleted(true);
            getRepository().save(abstractUser);
        }
    }

    @Override
    public CrudRepository<AbstractUser, ObjectId> getRepository() {
        return abstractUserRepository;
    }
}
