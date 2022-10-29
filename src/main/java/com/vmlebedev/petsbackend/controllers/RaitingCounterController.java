package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.convertors.RaitingView2Raiting;
import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.services.RaitingCounterService;
import com.vmlebedev.petsbackend.views.RaitingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/raiting")
@CrossOrigin
public class RaitingCounterController {

    private final RaitingCounterService service;
    private final RaitingView2Raiting converter;

    @Autowired
    public RaitingCounterController(RaitingCounterService service,RaitingView2Raiting converter){
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "/createRaiting")
    public ResponseEntity<?> createRaiting(@RequestBody RaitingView raitingView){
        Raiting raiting = converter.convert(raitingView);
        if(service.checkIfExist(raiting)){
            Raiting newRaiting = service.saveRaiting(raiting);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(newRaiting.getId())
                    .buildAndExpand(newRaiting.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            service.updateLikers(raiting);
            return ResponseEntity.status(201).build();
        }

    }

    @GetMapping(value = "/getLikesAmount")
    public ResponseEntity<Integer> getLikesAmount(@RequestParam String login){
        return ResponseEntity.ok(service.getLikesAmount(login));
    }



}
