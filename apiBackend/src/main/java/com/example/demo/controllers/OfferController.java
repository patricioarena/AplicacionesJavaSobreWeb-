package com.example.demo.controllers;

import com.example.demo.Application.IServices.IOfferService;
import com.example.demo.DataAccess.Models.Offer;
import com.example.demo.Domain.Datawrapper;
import com.example.demo.Domain.OfferDto;
import com.example.demo.Domain.UserDto;
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

    public OfferController(IOfferService offerService) {
        this._offerService = offerService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Datawrapper<List<OfferDto>>> getAll() {
        List<OfferDto> resultList = _offerService.getAllOffer();
        Datawrapper<List<OfferDto>> response = new Datawrapper<>(resultList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Datawrapper<OfferDto>> find(@PathVariable String id) {
        OfferDto resultOfferDto = _offerService.get(id);
        Datawrapper<OfferDto> response = new Datawrapper<>(resultOfferDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Datawrapper<OfferDto>> save(@RequestBody OfferDto offerDto) {
        OfferDto resultOfferDto = _offerService.save(offerDto);
        Datawrapper<OfferDto> response = new Datawrapper<>(resultOfferDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Datawrapper<OfferDto>> delete(@PathVariable String id) {
        OfferDto resultDelete = _offerService.deleted(id);
        Datawrapper<OfferDto> response = new Datawrapper<>(resultDelete);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
