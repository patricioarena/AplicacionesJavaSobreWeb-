package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IOfferService;
import com.example.demo.DataAccess.Models.Offer;
import com.example.demo.DataAccess.Repository.OfferRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class OfferService extends GenericService<Offer, ObjectId> implements IOfferService {

    OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository){
        this.offerRepository = offerRepository;
    }

    @Override
    public CrudRepository<Offer, ObjectId> getRepository() {
        return offerRepository;
    }
}
