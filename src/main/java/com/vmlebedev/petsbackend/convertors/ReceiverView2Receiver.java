package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.views.CommentView;
import com.vmlebedev.petsbackend.views.ReceiverView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReceiverView2Receiver implements Converter<ReceiverView, Receiver> {

    private final CommentView2Comment converter;

    @Autowired
    public ReceiverView2Receiver(CommentView2Comment converter){
        this.converter = converter;
    }

    @Override
    public Receiver convert(ReceiverView source) {
        List<Comment> list = new ArrayList<>();
        for (CommentView view:
                source.getComments()) {
           list.add(converter.convert(view));
        }
        return new Receiver("",source.getReceiverLogin(),list);
    }
}
