package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.models.UserForRaiting;
import com.vmlebedev.petsbackend.repository.RaitingCounterRepository;
import com.vmlebedev.petsbackend.repository.UserForRaitingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(RaitingCounterService.class)
class RaitingCounterServiceTest {

    @Autowired
    private RaitingCounterService raitingCounterService;
    @MockBean
    private RaitingCounterRepository raitingCounterRepository;

    @MockBean
    private UserForRaitingRepository userForRaitingRepository;

    @Test
    void findAll() {
        Raiting raiting1 = new Raiting("testId1","test1",null,null);
        Raiting raiting2 = new Raiting("testId2","test2",null,null);
        List<Raiting> raitingFromMock = new ArrayList<>();
        raitingFromMock.add(raiting1);
        raitingFromMock.add(raiting2);


        when(raitingCounterRepository.findAll()).thenReturn(Arrays.asList(
                raiting1,
                raiting2
        ));

        List<Raiting> tmp = (List<Raiting>) raitingCounterRepository.findAll();

        Assertions.assertEquals(raitingFromMock,tmp);
    }


    @Test
    void saveRaiting() {
        Raiting raiting = new Raiting("testId","test",null,null);
        Set<UserForRaiting> tmp = new HashSet<>();
        tmp.add(new UserForRaiting(null,"testLiker",null));
        raiting.setRaitingLogins(tmp);
        String uniqueKey = UUID.randomUUID().toString();
        raiting.setId(uniqueKey);

        for (UserForRaiting i: raiting.getRaitingLogins()
        ) {
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
            i.setRaiting(raiting);
        }
        when(raitingCounterRepository.save(raiting)).thenReturn(raiting);
        Raiting tmpNew = raitingCounterRepository.save(raiting);
        Assertions.assertEquals(raiting.getLogin(),tmpNew.getLogin());
    }

    @Test
    void getLikesAmount() {
        Raiting raiting = new Raiting("testId","test",null,null);
        when(userForRaitingRepository.findAllByRaiting(raiting)).thenReturn(Arrays.asList(
                new UserForRaiting()
        ));
        int i = 1;
        int j = userForRaitingRepository.findAllByRaiting(raiting).size();
        assertEquals(i, j);
    }


    @Test
    void updateLikers() {
        Raiting raiting = new Raiting("testId","test",null,null);
        when(raitingCounterRepository.findAllByLogin("test")).thenReturn(raiting);
        when(raitingCounterRepository.save(raiting)).thenReturn(raiting);

        Set<UserForRaiting> tmpSet = new HashSet<>();
        tmpSet.add(new UserForRaiting());

        Raiting tmpRaiting = raitingCounterRepository.findAllByLogin("test");
        tmpRaiting.setRaitingLogins(tmpSet);
        Raiting newTmpRaiting = raitingCounterRepository.save(raiting);

        Assertions.assertEquals(tmpRaiting.getLogin(),newTmpRaiting.getLogin());
    }
}