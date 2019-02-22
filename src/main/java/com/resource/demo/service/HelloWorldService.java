package com.resource.demo.service;

import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.http.data.HelloResponse;
import com.resource.demo.repository.MessageEntity;

public interface HelloWorldService {

    Iterable<HelloResponse> findAllMessages();

    HelloResponse saveMessage(HelloRequest helloRequest);

    HelloResponse findMessageById(Long id);

    void deleteMessageById(Long id);

    String formatMessage(String message);

    HelloResponse updateMessage(HelloResponse message);
}
