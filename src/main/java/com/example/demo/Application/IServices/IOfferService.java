package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.Offer;
import com.example.demo.Domain.OfferDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IOfferService extends IGenericService<Offer, ObjectId>{
    List<OfferDto> getAllOffer();
    OfferDto get(String id);
    OfferDto save(OfferDto offerDto);
    OfferDto deleted(String id);
}
