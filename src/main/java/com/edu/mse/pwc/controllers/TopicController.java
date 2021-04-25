package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
@RolesAllowed(value = {"MODERATOR", "ADMIN", "USER"})
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/{id}/")
    public TopicDto getTopic(@PathVariable Long id) {
        return topicService.getTopic(id);
    }

    @GetMapping
    public List<TopicDto> getAllTopic() {
        return topicService.getAllTopics();
    }

    @PostMapping
    public TopicDto createTopic(@RequestBody TopicDto topic) {
        return topicService.createTopic(topic);
    }

    //TODO CREATE REPLY NOT FOUND EX
    @ExceptionHandler(value = {IllegalArgumentException.class})
    private ResponseEntity<String> handleExceptions() {
        return new ResponseEntity<String>("Error...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
