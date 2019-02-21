package com.resource.demo.http;

import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.http.data.MessageResponse;
import com.resource.demo.repository.MessageEntity;
import com.resource.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class HelloWorldController {

    @Autowired
    MessageRepository messageRepository;

    MessageResponse messageResponse = new MessageResponse();

    @GetMapping
    public Iterable<MessageEntity> getResponse() {

        return messageRepository.findAll();
    }

    @PostMapping
    public MessageEntity postMessage(@RequestBody HelloRequest request) {

        String mensagem = String.format("Mensagem: %s", request.getMessage());

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(mensagem);

        return messageRepository.save(messageEntity);
    }

    @GetMapping("/{id}")
    public MessageEntity findById(@PathVariable Long id) {
        return messageRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public Object deleteMessage(@PathVariable Long id){
        try {
            messageRepository.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
