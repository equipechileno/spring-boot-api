package com.resource.demo.service;

import com.resource.demo.converter.MessageConverter;
import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.http.data.HelloResponse;
import com.resource.demo.repository.MessageEntity;
import com.resource.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWordServiceImpl implements HelloWorldService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageConverter messageConverter;

    @Override
    public Iterable<HelloResponse> findAllMessages() {
        return messageConverter.toResponses(messageRepository.findAll());
    }

    @Override
    public HelloResponse saveMessage(HelloRequest helloRequest) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(this.formatMessage(helloRequest.getMessage()));
        return messageConverter.toResponse(messageRepository.save(messageEntity));
    }

    @Override
    public HelloResponse findMessageById(Long id) {
        return messageConverter.toResponse(messageRepository.findById(id).get());
    }

    @Override
    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public String formatMessage(String message) {
        return String.format("Mensagem: %s", message);
    }

    @Override
    public HelloResponse updateMessage(HelloResponse message) {
        return messageConverter.toResponse(messageRepository.save(messageConverter.toEntity(message)));
    }
}
