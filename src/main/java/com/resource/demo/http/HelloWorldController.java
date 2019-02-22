package com.resource.demo.http;

import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.http.data.HelloResponse;
import com.resource.demo.http.data.MessageResponse;
import com.resource.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/messages")
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;

    MessageResponse messageResponse = new MessageResponse();

    @GetMapping
    public Iterable<HelloResponse> getResponse() {
        return helloWorldService.findAllMessages();
    }

    @PostMapping
    public HelloResponse postMessage(@RequestBody HelloRequest request) {
        return helloWorldService.saveMessage(request);
    }

    @GetMapping("/{id}")
    public HelloResponse findById(@PathVariable Long id) {
        return helloWorldService.findMessageById(id);
    }

  @PutMapping("/{id}")
    public ResponseEntity<HelloResponse> putMessageEntity(@PathVariable Long id, @RequestBody HelloRequest request){
      HelloResponse helloResponse = helloWorldService.findMessageById(id);
      if (helloResponse != null) {
          return ResponseEntity.ok(helloWorldService.updateMessage(helloResponse));
      } else {
          return ResponseEntity.badRequest().build();
      }
    }
  
    @DeleteMapping("/{id}")
    public Object deleteMessage(@PathVariable Long id){
        try {
            helloWorldService.deleteMessageById(id);
        } catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }
}
