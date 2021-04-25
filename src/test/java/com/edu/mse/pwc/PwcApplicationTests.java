package com.edu.mse.pwc;

import com.edu.mse.pwc.dtos.TopicDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:test.properties")
class PwcApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    void contextLoads() throws Exception {
        TopicDto topic = TopicDto.builder().title("test topic").userId(1L).build();
        Gson gson = new Gson();
        String topicToJson = gson.toJson(topic);
        System.out.println(topicToJson);

        MockHttpServletRequestBuilder topicPost = post("/api/topics").contentType(MediaType.APPLICATION_JSON).content(topicToJson);

        mvc.perform(topicPost).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

}
