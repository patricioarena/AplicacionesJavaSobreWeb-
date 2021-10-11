package com.example.demo.controllers;

import com.example.demo.Application.IServices.IOfferService;
import com.example.demo.DataAccess.Models.Offer;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/offer")
@CrossOrigin("*")
public class OfferController {

    private IOfferService _offerService;

    public OfferController(IOfferService offerService){
        this._offerService = offerService;
    }

    @GetMapping(value = "/getAll")
    public List<Offer> getAll(){
        return _offerService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Offer find(@PathVariable ObjectId id){
        return _offerService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Offer> save(@RequestBody Offer offer){
        Offer obj = _offerService.save(offer);
        return new ResponseEntity<Offer>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Offer> delete(@PathVariable ObjectId id){
        Offer offer = _offerService.get(id);
        if(offer != null){
            _offerService.delete(id);
        }else{
            return new ResponseEntity<Offer>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Offer>(offer, HttpStatus.OK);
    }
}
