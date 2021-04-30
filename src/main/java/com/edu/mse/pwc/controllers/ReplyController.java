package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.services.ReplyService;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")

public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createReply(@RequestBody ReplyDto reply) {
        P.syso(reply);
        return replyService.createReply(reply);
    }

    //    @GetMapping("/topicId/{topicId}/")
//    public List<ReplyDto> getRepliesForTopic(@PathVariable Long topicId) {
//        return replyService.getRepliesForTopic(topicId);
//    }
    @GetMapping("/topicId/{topicId}/{page}/{size}/")
    public ApiResponse<List<ReplyDto>> getRepliesForTopic(@PathVariable Long topicId, @PathVariable(value = "page") Integer pageNumber,
                                                          @PathVariable(value = "size") Integer pageSize) {
        return replyService.getPageWithRepliesForTopic(topicId, pageNumber, pageSize);
    }


    @PutMapping("/{replyId}/")
    public ReplyDto updateReply(@PathVariable Long replyId, @RequestBody ReplyDto reply) {
        return replyService.updateReply(replyId, reply);
    }
}
