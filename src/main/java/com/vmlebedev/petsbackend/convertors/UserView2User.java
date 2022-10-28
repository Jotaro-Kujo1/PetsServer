package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.views.UserView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserView2User implements Converter<UserView, User> {
    @Override
    public User convert(UserView source) {
        return new User("",source.getLogin(),source.getPassword(),false);
    }
}
