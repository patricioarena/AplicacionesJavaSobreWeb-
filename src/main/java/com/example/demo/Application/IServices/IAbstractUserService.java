package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.AbstractUser;
import org.bson.types.ObjectId;

public interface IAbstractUserService extends IGenericService <AbstractUser, ObjectId>{

    void setDeleted(AbstractUser user);
    void setDeleted(ObjectId id);
}
