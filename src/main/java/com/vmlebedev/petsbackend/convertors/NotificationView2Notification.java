package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.Notification;
import com.vmlebedev.petsbackend.views.NotificationView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotificationView2Notification implements Converter<NotificationView, Notification> {
    @Override
    public Notification convert(NotificationView source) {
        return new Notification("",source.getProfimg(),source.getText(),source.getSenderLogin(),source.getReceiverLogin(),"");
    }
}
