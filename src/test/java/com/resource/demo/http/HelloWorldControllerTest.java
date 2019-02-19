package com.resource.demo.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resource.demo.http.data.HelloRequest;
import com.resource.demo.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    private HelloRequest helloRequest;

    @MockBean
    MessageRepository messageRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        helloRequest = new HelloRequest("estoy cansadito");
    }

    @Test
    public void getResponse() {

    }

    @Test
    public void postMessage() throws Exception {

        // TODO mockar retorno do MessageRepository

        mockMvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(new ObjectMapper().writeValueAsString(helloRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("estoy cansadito"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteMessage() {
    }
}