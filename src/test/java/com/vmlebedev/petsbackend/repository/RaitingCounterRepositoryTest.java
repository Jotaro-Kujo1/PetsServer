package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Raiting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RaitingCounterRepositoryTest {

    @Autowired
    private RaitingCounterRepository raitingCounterRepository;

    @Test
    public void saveRaiting(){
        Raiting raiting = new Raiting();
        raiting.setLogin("test");
        raitingCounterRepository.save(raiting);
        Assertions.assertNotNull(raiting.getId());
        raiting.getId();
    }

    @Test
    public void getRaiting(){
        init();
        Raiting raiting = raitingCounterRepository.findAllByLogin("testLogin1");
        Assertions.assertEquals(raiting.getLogin(),"testLogin1");
    }

    @Test
    public void getRaitings(){
        init();
        List<Raiting> raitingList = (List<Raiting>) raitingCounterRepository.findAll();
        Assertions.assertEquals(raitingList.size(),3);
    }

    @Test
    public void updateRaiting(){
        init();
        Raiting raiting = raitingCounterRepository.findAllByLogin("testLogin1");
        raiting.setLogin("newTestLogin1");
        Raiting newRaiting = raitingCounterRepository.save(raiting);
        Assertions.assertEquals(newRaiting.getLogin(),"newTestLogin1");
    }

    @Test
    public void deleteUser(){
        init();
        Raiting raiting = raitingCounterRepository.findAllByLogin("testLogin1");
        raitingCounterRepository.delete(raiting);
        List<Raiting> list = (List<Raiting>) raitingCounterRepository.findAll();
        Assertions.assertEquals(list.size(),2);
    }

    @Test
    void findAllByLogin() {
        init();
        Raiting raiting = raitingCounterRepository.findAllByLogin("testLogin2");
        Assertions.assertEquals(raiting.getLogin(),"testLogin2");
    }

    @Test
    void deleteByLogin() {
        init();
        raitingCounterRepository.deleteByLogin("testLogin2");
        List<Raiting> list = (List<Raiting>) raitingCounterRepository.findAll();
        Assertions.assertEquals(list.size(),2);
    }

    private void init(){
        raitingCounterRepository.save(new Raiting("testId1","testLogin1",null,null));
        raitingCounterRepository.save(new Raiting("testId2","testLogin2",null,null));
        raitingCounterRepository.save(new Raiting("testId3","testLogin3",null,null));
    }
}