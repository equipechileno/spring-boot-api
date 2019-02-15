package com.resource.demo.http;

import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.http.data.HelloResponse;
import com.resource.demo.http.data.MessageResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping
    public HelloResponse getResponse() {

        HelloResponse helloResponse = new HelloResponse(1L, "descricao");

        return helloResponse;
    }

    @PostMapping
    public MessageResponse postMessage(@RequestBody HelloRequest request) {

        String mensagem = String.format("Mensagem: %s", request.getMessage());

        MessageResponse response = new MessageResponse(mensagem);

        return response;
    }
}
