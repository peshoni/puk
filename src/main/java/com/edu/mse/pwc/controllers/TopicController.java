package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.exceptions.DuplicateTopicException;
import com.edu.mse.pwc.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<TopicDto> createTopic(@RequestBody TopicDto topic) {
        try {
            TopicDto newTopic = topicService.createTopic(topic);
            return new ApiResponse<TopicDto>(HttpStatus.OK.value(), "Created successfully", newTopic);
        } catch (DuplicateTopicException ex) {
            return new ApiResponse<TopicDto>(HttpStatus.ACCEPTED.value(), "Duplicated topic", null);
        }
    }

    @GetMapping("/{page}/{size}/")
    public ApiResponse<List<TopicDto>> getPageOFTopics(
            @PathVariable(value = "page") Integer pageNumber,
            @PathVariable(value = "size") Integer pageSize) {
        return topicService.getPageWithTopics(pageNumber, pageSize);
    }

//    @ExceptionHandler(value = {TopicNotFoundException.class, ReplyNotFoundException.class})
//    protected ResponseEntity<ErrorDto> handleException(Exception e) {
//        ErrorDto build = ErrorDto.builder().message(e.getMessage()).build();
//        ResponseEntity<ErrorDto> error = new ResponseEntity<>(build, HttpStatus.INTERNAL_SERVER_ERROR);
//        return error;
//    }

}
