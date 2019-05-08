package com.resource.demo.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.http.data.HelloResponse;
import com.resource.demo.service.HelloWorldService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    private HelloRequest helloRequest;
    private HelloResponse helloResponse;
    private ArrayList<HelloResponse> helloResponses;

    @MockBean
    HelloWorldService helloWorldService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        helloRequest = new HelloRequest("estoy cansadito");
        helloResponse = new HelloResponse(100L, "estoy cansadito");
        helloResponses = new ArrayList<>();
        helloResponses.add(helloResponse);
        helloResponses.add(new HelloResponse(101L, "estoy cansadito1"));
        helloResponses.add(new HelloResponse(102L, "estoy cansadito2"));
    }

    @Test
    public void shouldReturnMessageList() throws Exception {
        Mockito.when(helloWorldService.findAllMessages()).thenReturn(helloResponses);
        for (int i = 0; i < helloResponses.size(); i++) {
            mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[" + i + "].description").isNotEmpty())
                .andExpect(jsonPath("$[" + i + "].id").isNotEmpty());
        }
    }

    @Test
    public void shouldReturnMessageSaved() throws Exception {
        Mockito.when(helloWorldService.saveMessage(any(HelloRequest.class))).thenReturn(helloResponse);
        mockMvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(new ObjectMapper().writeValueAsString(helloRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("estoy cansadito"))
                .andExpect(jsonPath("$.id").value("100"));
    }

    @Test
    public void shouldReturnMessageById() throws Exception {
        Mockito.when(helloWorldService.findMessageById(any(Long.class))).thenReturn(helloResponse);
        mockMvc.perform(get("/messages/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("estoy cansadito"))
                .andExpect(jsonPath("$.id").value("100"));
    }

    @Test
    public void shouldReturnNoContentMessageOnDeleteById() throws Exception {
        mockMvc.perform(delete("/messages/1"))
        .andExpect(status().isNoContent());
    }
}