package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.models.UserForRaiting;
import com.vmlebedev.petsbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Receiver> findAll(){
        return (List<Receiver>) commentRepository.findAll();
    }

    public boolean checkIfExist(Receiver receiver){
        boolean check = findAll().stream().anyMatch(s -> s.getReceiverLogin().equals(receiver.getReceiverLogin()));
        return !check;
    }

    public boolean checkIfExist(String login){
        boolean check = findAll().stream().anyMatch(s -> s.getReceiverLogin().equals(login));
        return check;
    }

    public Receiver saveReceiver(Receiver receiver){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date;
        String uniqueKey = UUID.randomUUID().toString();
        receiver.setId(uniqueKey);
        receiver.setComments(receiver.getTmpComments());
        for (Comment i: receiver.getComments()
        ) {
            date = formatter.format(new Date());
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
            i.setReceiver_login(receiver);
            i.setDate(date);
        }
        return commentRepository.save(receiver);
    }

    public void updateComments(Receiver receiver){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date;
        receiver.setComments(receiver.getTmpComments());
        String uniqueKey = "";

        Receiver oldReceiver = commentRepository.findAllByReceiverLogin(receiver.getReceiverLogin());

        //List<Comment> oldComments = oldReceiver.getComments();
        Comment [] oldComments = oldReceiver.getComments().toArray(new Comment[0]);
        List<Comment> tmpComments = receiver.getComments();

        for (Comment i:
             oldComments) {
            tmpComments.add(new Comment("",i.getSenderLogin(),i.getProfimg(),i.getText(),null,i.getDate()));
        }
        //tmpComments.addAll(oldComments);

        for (Comment i:
             tmpComments) {
            date = formatter.format(new Date());
            uniqueKey = UUID.randomUUID().toString();
            i.setId(uniqueKey);
            i.setReceiver_login(receiver);
            if(i.getDate() == null)i.setDate(date);
        }

        receiver.setComments(tmpComments);

        commentRepository.deleteByReceiverLogin(receiver.getReceiverLogin());
        commentRepository.save(receiver);
    }

    public List<Comment> getAllCommentsForUser(String login){
        if(checkIfExist(login)){
            return commentRepository.findAllByReceiverLogin(login).getComments();
        }else return null;
    }

    public void updatePictureAllConversations(String login,String profimg){
        commentRepository.updateComment(login,profimg);
    }
}
