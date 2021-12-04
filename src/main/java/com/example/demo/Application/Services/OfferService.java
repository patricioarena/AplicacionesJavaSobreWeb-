package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IOfferService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.Offer;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.OfferRepository;
import com.example.demo.DataAccess.Repository.RoleRepository;
import com.example.demo.Domain.OfferDto;
import com.example.demo.Domain.RoleDto;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Configuration
public class OfferService extends GenericService<Offer, ObjectId> implements IOfferService {


    private final OfferRepository offerRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository, CommonResourcesRepository commonResourcesRepository) {
        this.offerRepository = offerRepository;
        this.commonResourcesRepository = commonResourcesRepository;
    }

    public List<OfferDto> getAllOffer() {
        ModelMapper modelMapper = new ModelMapper();
        Iterable<Offer> iter = this.getRepository().findAll();
        List<OfferDto> listDtos = new ArrayList<>();
        iter.forEach(offer -> listDtos.add(modelMapper.map(offer, OfferDto.class)));
        return listDtos;
    }

    public OfferDto get(String id) {
        ModelMapper modelMapper = new ModelMapper();
        Offer offer = this.getRepository().findById(new ObjectId(id)).get();
        return modelMapper.map(offer, OfferDto.class);
    }

    public OfferDto save(OfferDto offerDto) {
        ModelMapper modelMapper = new ModelMapper();
        Offer offer = modelMapper.map(offerDto, Offer.class);
        offer.set_id(new ObjectId(offerDto.get_id()));
        var temp1 = this.getRepository().save(offer);
        return modelMapper.map(temp1, OfferDto.class);
    }

    public OfferDto deleted(String id) {
        OfferDto offerDto = new OfferDto();
        var result = this.commonResourcesRepository.deleted(id, Database.offerCollection);
        return (result == 1) ? this.get(id) : offerDto;
    }

    @Override
    public CrudRepository<Offer, ObjectId> getRepository() {
        return offerRepository;
    }
}
