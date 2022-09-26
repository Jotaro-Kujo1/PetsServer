package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.services.RaitingCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/raiting")
@CrossOrigin
public class RaitingCounterController {

    private RaitingCounterService service;

    @Autowired
    public RaitingCounterController(RaitingCounterService service){
        this.service = service;
    }

    @PostMapping(value = "/createRaiting")
    public ResponseEntity<Raiting> createRaiting(@RequestBody Raiting raiting){
        if(service.checkIfExist(raiting)){
            Raiting newRaiting = service.saveRaiting(raiting);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(newRaiting.getId())
                    .buildAndExpand(newRaiting.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

}
