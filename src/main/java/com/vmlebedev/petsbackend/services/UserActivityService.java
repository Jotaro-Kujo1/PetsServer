package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Activity;
import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.models.UserActivity;
import com.vmlebedev.petsbackend.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserActivityService {
    private UserActivityRepository userActivityRepository;

    @Autowired
    public UserActivityService(UserActivityRepository userActivityRepository){
        this.userActivityRepository = userActivityRepository;
    }

    public List<UserActivity> findAll(){
        return (List<UserActivity>) userActivityRepository.findAll();
    }

    public boolean checkIfExist(UserActivity userActivity){
        boolean check = findAll().stream().anyMatch(s->s.getLogin().equals(userActivity.getLogin()));
        return !check;
    }

    public boolean checkIfExist(String login){
        return findAll().stream().anyMatch(s->s.getLogin().equals(login));
    }

    public UserActivity saveUserActivity(UserActivity userActivity){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date;
        String uniqueKey = UUID.randomUUID().toString();
        userActivity.setId(uniqueKey);
        userActivity.setActivities(userActivity.getTmpActivities());
        for (Activity i: userActivity.getActivities()
        ) {
            date = formatter.format(new Date());
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
            i.setUserActivity(userActivity);
            i.setDate(date);
        }
        return userActivityRepository.save(userActivity);
    }

    public List<Activity> getActivitiesForUser(String login){
        if(checkIfExist(login)){
            return userActivityRepository.findAllByLogin(login).getActivities();
        } else return null;
    }

    public void updateActivities(UserActivity userActivity){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date;
        userActivity.setActivities(userActivity.getTmpActivities());
        String uniqueKey = "";

        UserActivity oldUserActivity = userActivityRepository.findAllByLogin(userActivity.getLogin());

        Activity [] oldActivities = oldUserActivity.getActivities().toArray(new Activity[0]);
        List<Activity> tmpActivities = userActivity.getActivities();

        for (Activity i:
                oldActivities) {
            tmpActivities.add(new Activity("",i.getActivityName(),null,i.getDate(),i.getActivityimg()));
        }


        for (Activity i:
                tmpActivities) {
            date = formatter.format(new Date());
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
            i.setUserActivity(userActivity);
            if(i.getDate() == null)i.setDate(date);
        }

        userActivity.setActivities(tmpActivities);

        userActivityRepository.deleteByLogin(userActivity.getLogin());
        userActivityRepository.save(userActivity);
    }
}
