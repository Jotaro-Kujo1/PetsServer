package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.views.ConversationView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConversationView2Conversation implements Converter<ConversationView, Conversation> {
    @Override
    public Conversation convert(ConversationView source) {
        return new Conversation("", source.getSenderLogin(), source.getReceiverLogin(),source.getProfimg());
    }
}
