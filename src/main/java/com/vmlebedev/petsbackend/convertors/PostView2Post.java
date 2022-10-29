package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.postBuilder.*;
import com.vmlebedev.petsbackend.views.PostView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostView2Post implements Converter<PostView, Post> {
    @Override
    public Post convert(PostView source) {
        PostBuilder builder = new SimplifiedPostBuilder();
        PostDirector postDirector = new PostDirector(builder);
        return postDirector.createSimplePost(
                source.getImg(),
                source.getDescription(),
                source.getAddress(),
                source.getLogin(),
                source.getHandler(),
                source.isState(),
                source.getProfimg());
    }
}
