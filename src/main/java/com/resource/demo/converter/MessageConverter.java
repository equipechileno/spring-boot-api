package com.resource.demo.converter;

import com.resource.demo.http.data.HelloResponse;
import com.resource.demo.repository.MessageEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MessageConverter {

    HelloResponse helloResponse;

    public HelloResponse toResponse(MessageEntity messageEntity){
        return helloResponse = new HelloResponse(messageEntity.getId(), messageEntity.getMessage());
    }

    public Set<HelloResponse> toResponses(Iterable<MessageEntity> messages){
        Set<HelloResponse> responses = new HashSet<>();
        for (MessageEntity message: messages) {
            responses.add(new HelloResponse(message.getId(), message.getMessage()));
        }
        return responses;
    }

    public MessageEntity toEntity(HelloResponse message){
        return new MessageEntity(message.getId(), message.getDescription());
    }
}
