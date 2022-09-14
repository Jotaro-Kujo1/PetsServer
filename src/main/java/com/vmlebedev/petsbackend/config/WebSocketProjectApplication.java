package com.vmlebedev.petsbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//Включает WebSocket сервер
public class WebSocketProjectApplication implements WebSocketMessageBrokerConfigurer {

    //Регистрация конечной точки, которую клиенты будут использовать для подключения к WebSocket - серверу.
    //SockJS — для браузеров, которые не поддерживают Websocket.

    //STOMP — это Simple Text Oriented Messaging Protocol. Это протокол обмена сообщениями, задающий формат и правила обмена.
    //Сам по себе WebSocket не дает таких вещей (более высокого уровня), как отправка сообщений пользователям, подписанным на тему, или отправка сообщений конкретному пользователю.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }


    //Настраиваем брокер сообщений, который будет использоваться для направления сообщений от одного клиента к другому.
    //В первой строке мы говорим, что сообщения, чей адрес (куда отправлены) начинается с  «/app«, должны быть направлены в методы, занимающиеся обработкой сообщений.
    //Во второй строке мы говорим, что  сообщения, чей адрес начинается с  «/topic«, должны быть направлены в брокер сообщений. Брокер перенаправляет сообщения всем клиентам, подписанным на тему.
    //В примере у нас используется встроенный брокер, но можно использовать и полноценный брокер, такой как RabbitMQ или ActiveMQ.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic/");
        registry.setApplicationDestinationPrefixes("/app");
    }

}
