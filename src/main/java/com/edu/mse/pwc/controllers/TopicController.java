package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.services.TopicService;
import com.edu.mse.pwc.utils.JwtUtils;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
@RolesAllowed(value = {"MODERATOR", "ADMIN", "USER"})
public class TopicController {
    private final TopicService topicService;

    @PostMapping
    public ApiResponse<TopicDto> createTopic(@RequestBody TopicDto topic) {
        TopicDto newTopic = topicService.createTopic(topic);
        return new ApiResponse<TopicDto>(HttpStatus.OK.value(), "Created successfully", newTopic);
    }

    @GetMapping("/{page}/{size}/")
    public ApiResponse<List<TopicDto>> getPageOFTopics(
            @PathVariable(value = "page") Integer pageNumber,
            @PathVariable(value = "size") Integer pageSize) {
        ApiResponse<List<TopicDto>> response = topicService.getPageWithTopics(pageNumber, pageSize);
        return response;
    }

    @GetMapping("/saw/{userId}/{topicId}/")
    public ApiResponse<TopicDto> userSawTopic(@PathVariable long userId, @PathVariable long topicId) {
        this.topicService.markTopicAsSeen(userId, topicId);
        return new ApiResponse<TopicDto>(HttpStatus.OK.value(), "Counted", null);
    }


    @PutMapping()
    public TopicDto updateTopic(HttpServletRequest request, @RequestBody TopicDto topic) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1];
        P.syso(token);
        JwtUtils.JwtClaims claims = JwtUtils.getClaims(token);
        return topicService.update(topic);
    }

}
