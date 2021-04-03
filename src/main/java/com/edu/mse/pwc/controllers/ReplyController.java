package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.services.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createTopic(@RequestBody ReplyDto reply) {
        return replyService.createTopic(reply);
    }
}
