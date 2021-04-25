package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.mappers.ReplyMapper;
import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.repository.ReplyRepository;
import com.edu.mse.pwc.persistence.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final TopicRepository topicRepository;

    public ReplyDto createReply(ReplyDto reply) {
        Long topicId = reply.getTopicId();
        Optional<TopicEntity> byId = topicRepository.findById(topicId);
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("No such topic");
        }

        ReplyEntity replyEntity = replyMapper.replyDtoToEntity(reply);
        replyEntity.setTopic(byId.get());
        ReplyEntity newReplyEntity = replyRepository.save(replyEntity);

        ReplyDto replyDto = replyMapper.replyEntityToDto(newReplyEntity);
        return replyDto;
    }

    public List<ReplyDto> getRepliesFor(long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("no such topic");
        }
        return byId.get().getReply().stream().map(reply -> replyMapper.replyEntityToDto(reply)).collect(Collectors.toList());
    }

}
