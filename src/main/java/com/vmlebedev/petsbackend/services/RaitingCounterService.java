package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.models.UserForRaiting;
import com.vmlebedev.petsbackend.repository.RaitingCounterRepository;
import com.vmlebedev.petsbackend.repository.UserForRaitingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RaitingCounterService {

    private RaitingCounterRepository repository;
    private UserForRaitingRepository userForRaitingRepository;

    @Autowired
    public RaitingCounterService(RaitingCounterRepository repository,UserForRaitingRepository userForRaitingRepository){
        this.repository = repository;
        this.userForRaitingRepository = userForRaitingRepository;
    }

    public List<Raiting> findAll(){
        return (List<Raiting>) repository.findAll();
    }

    public boolean checkIfExist(Raiting raiting){
        boolean check = findAll().stream().anyMatch(s -> s.getLogin().equals(raiting.getLogin()));
        return !check;
    }

    public boolean checkIfExist(String login){
        boolean check = findAll().stream().anyMatch(s -> s.getLogin().equals(login));
        return check;
    }

    public Raiting saveRaiting(Raiting raiting){
        String uniqueKey = UUID.randomUUID().toString();
        raiting.setId(uniqueKey);
        raiting.setRaitingLogins(raiting.getTmpLogins());
        for (UserForRaiting i: raiting.getRaitingLogins()
             ) {
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
            i.setRaiting(raiting);
        }
        return repository.save(raiting);
    }

    public int getLikesAmount(String login){
        if(checkIfExist(login)){//true
            Raiting raiting = repository.findAllByLogin(login);
            return userForRaitingRepository.findAllByRaiting(raiting).size();
            //return repository.findAllByLogin(login).getRaitingLogins().size();
        }else return 0;
    }




    public void updateLikers(Raiting raiting){
        raiting.setRaitingLogins(raiting.getTmpLogins());
        String uniqueKey = "";

        Raiting oldRaiting = repository.findAllByLogin(raiting.getLogin());

        UserForRaiting [] oldLikers = oldRaiting.getRaitingLogins().toArray(new UserForRaiting[0]);

        Set<UserForRaiting> tmpLikersSet = raiting.getTmpLogins();

        for (UserForRaiting oldLiker : oldLikers) {
            boolean flag = false;
            for (UserForRaiting i:
                 tmpLikersSet) {
                if(i.getLiker().equals(oldLiker.getLiker())){
                    flag = true;
                }
            }
            if(!flag)tmpLikersSet.add(new UserForRaiting("", oldLiker.getLiker(), null));

        }

        for (UserForRaiting i:
             tmpLikersSet) {
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
            i.setRaiting(raiting);
        }

        raiting.setRaitingLogins(tmpLikersSet);

        repository.deleteByLogin(raiting.getLogin());
        repository.save(raiting);
    }
}
