package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.views.CommentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentView2Comment implements Converter<CommentView, Comment> {
    @Override
    public Comment convert(CommentView source) {
        return new Comment("",source.getSenderLogin(),source.getProfimg(),source.getText(),null,"");
    }
}
