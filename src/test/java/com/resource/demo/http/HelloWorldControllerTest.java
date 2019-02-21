package com.resource.demo.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.repository.MessageEntity;
import com.resource.demo.repository.MessageRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    private HelloRequest helloRequest;
    private MessageEntity messageEntity;
    private ArrayList<MessageEntity> messageEntities;

    @MockBean
    MessageRepository messageRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        helloRequest = new HelloRequest("estoy cansadito");
        messageEntity = new MessageEntity(100L, "estoy cansadito");
        messageEntities = new ArrayList<>();
        messageEntities.add(messageEntity);
        messageEntities.add(new MessageEntity(101L, "estoy cansadito1"));
        messageEntities.add(new MessageEntity(102L, "estoy cansadito2"));
    }

    @Test
    public void getResponse() throws Exception {
        Mockito.when(messageRepository.findAll()).thenReturn(messageEntities);
        for (int i = 0; i < messageEntities.size(); i++) {
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[" + i + "].message").isNotEmpty())
                .andExpect(jsonPath("$[" + i + "].id").isNotEmpty());
        }
    }

    @Test
    public void postMessage() throws Exception {
        Mockito.when(messageRepository.save(any(MessageEntity.class))).thenReturn(messageEntity);

        mockMvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(new ObjectMapper().writeValueAsString(helloRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("estoy cansadito"))
                .andExpect(jsonPath("$.id").value("100"));
    }

    @Test
    public void findById() throws Exception {
        Mockito.when(messageRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(messageEntity));
        mockMvc.perform(get("/messages/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("estoy cansadito"))
                .andExpect(jsonPath("$.id").value("100"));
    }

    @Test
    public void deleteMessage() throws Exception {
        mockMvc.perform(delete("/messages/1"))
        .andExpect(status().isNotFound());

    }
}