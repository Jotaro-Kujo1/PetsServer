package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.models.UserForRaiting;
import com.vmlebedev.petsbackend.views.RaitingView;
import com.vmlebedev.petsbackend.views.UserForRaitingView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RaitingView2Raiting implements Converter<RaitingView, Raiting> {
    private final UserForRaitingView2UserForRaiting converter;

    public RaitingView2Raiting(UserForRaitingView2UserForRaiting converter){
        this.converter = converter;
    }

    @Override
    public Raiting convert(RaitingView source) {
        Set<UserForRaiting> raitings = new HashSet<>();
        for (UserForRaitingView view:
             source.getRaitingLogins()) {
            raitings.add(converter.convert(view));
        }
        return new Raiting("",source.getLogin(),raitings);
    }
}
