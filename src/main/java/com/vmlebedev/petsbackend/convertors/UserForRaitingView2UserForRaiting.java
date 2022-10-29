package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.UserForRaiting;
import com.vmlebedev.petsbackend.views.UserForRaitingView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserForRaitingView2UserForRaiting implements Converter<UserForRaitingView, UserForRaiting> {
    @Override
    public UserForRaiting convert(UserForRaitingView source) {
        return new UserForRaiting("",source.getLiker(),null);
    }
}
