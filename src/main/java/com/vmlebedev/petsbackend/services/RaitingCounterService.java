package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.models.UserForRaiting;
import com.vmlebedev.petsbackend.repository.RaitingCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RaitingCounterService {

    private RaitingCounterRepository repository;

    @Autowired
    public RaitingCounterService(RaitingCounterRepository repository){
        this.repository = repository;
    }

    public List<Raiting> findAll(){
        return (List<Raiting>) repository.findAll();
    }

    public boolean checkIfExist(Raiting raiting){
        boolean check = findAll().stream().anyMatch(s -> s.getLogin().equals(raiting.getLogin()));
        return !check;
    }

    public Raiting saveRaiting(Raiting raiting){
        String uniqueKey = UUID.randomUUID().toString();
        raiting.setId(uniqueKey);
        for (UserForRaiting i: raiting.getRaitingLogins()
             ) {
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
        }
        return repository.save(raiting);
    }

    public int getLikesAmount(String login){
        return repository.findAllByLogin(login).getRaitingLogins().size();
    }
}
