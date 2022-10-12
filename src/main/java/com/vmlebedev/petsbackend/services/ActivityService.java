package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Activity;
import com.vmlebedev.petsbackend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll(){
        return (List<Activity>) activityRepository.findAll();
    }

    public Activity findAllByLogin(String login){
        if(checkIfExist(login)) {
            return activityRepository.findAllByLogin(login);
        }else return null;
    }

    public boolean checkIfExist(Activity activity){
        boolean check = findAll().stream().anyMatch(s -> s.getLogin().equals(activity.getLogin()));
        return !check;
    }

    public boolean checkIfExist(String login){
        return findAll().stream().anyMatch(s->s.getLogin().equals(login));
    }

    public Activity createActivity(Activity activity){
        if(checkIfExist(activity)){
            String uniqueKey = UUID.randomUUID().toString();
            activity.setId(uniqueKey);
            return activityRepository.save(activity);
        }return null;
    }

    public void updateCommentActivity(int commentActivity, String login){
        activityRepository.updateCommentActivity(commentActivity,login);
    }

    public void updateLikeActivity(int likeActivity, String login){
        activityRepository.updateLikeActivity(likeActivity,login);
    }

    public void updatePostActivity(int postActivity, String login){
        activityRepository.updatePostActivity(postActivity,login);
    }


}
