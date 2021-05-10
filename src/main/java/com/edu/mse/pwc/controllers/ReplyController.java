package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.services.ReplyService;
import com.edu.mse.pwc.services.UserService;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")

public class ReplyController {
    private final UserService userService;
    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createReply(@RequestBody ReplyDto reply) {
        P.syso(reply);
        return replyService.createReply(reply);
    }


    @GetMapping("/topicId/{topicId}/{page}/{size}/")
    public ApiResponse<TopicDto> getRepliesForTopic(@PathVariable Long topicId, @PathVariable(value = "page") Integer pageNumber,
                                                    @PathVariable(value = "size") Integer pageSize) {
        return replyService.getPageWithRepliesForTopic(topicId, pageNumber, pageSize);
    }

    /**
     * Update {@link com.edu.mse.pwc.persistence.entities.ReplyEntity} according {@link ReplyDto} data
     *
     * @param reply {@link ReplyDto}
     * @return {@link ReplyDto}
     */
    @PutMapping()
    public ReplyDto updateReply(@RequestBody ReplyDto reply) {
        P.syso(reply);
        return replyService.updateReply(reply);
    }
}
