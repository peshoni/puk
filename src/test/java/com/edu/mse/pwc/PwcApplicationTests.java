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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	void testTopicCreation() throws Exception {
		TopicDto topic = TopicDto.builder().title("Test topic").userId(1L).build();
		Gson gson = new Gson();
		String topicJson = gson.toJson(topic);

		MockHttpServletRequestBuilder topicPost = post("/api/topics")
				.contentType(MediaType.APPLICATION_JSON)
				.content(topicJson);

		mvc.perform(topicPost)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.title").value("Test topic"))
				.andExpect(jsonPath("$.createdAt").exists())
				.andExpect(jsonPath("$.modifiedAt").exists());
	}

	@Test
	@Order(2)
	void testGetTopics() throws Exception {
		MockHttpServletRequestBuilder topicPost = get("/api/topics").contentType(MediaType.APPLICATION_JSON);

		mvc.perform(topicPost)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].title").value("Test topic"));
	}

}
