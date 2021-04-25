package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.services.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createTopic(@RequestBody ReplyDto reply) {
        return replyService.createReply(reply);
    }

    @GetMapping("/topicId/{topicId}")
    public List<ReplyDto> getReplies(@PathVariable long topicId) {
        return replyService.getRepliesFor(topicId);
    }
    //ADD list replies
    //add update reply
    // @PutMapping("")


}
