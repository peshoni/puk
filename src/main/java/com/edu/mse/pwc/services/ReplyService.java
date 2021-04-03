package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.mappers.ReplyMapper;
import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.repository.ReplyRepository;
import com.edu.mse.pwc.persistence.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final TopicRepository topicRepository;

    public ReplyDto createTopic(ReplyDto reply) {
        Long topicId = reply.getTopicId();
        Optional<TopicEntity> byId = topicRepository.findById(topicId);
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("No such topic");
        }

        ReplyEntity replyEntity = replyMapper.replyDtoToEntity(reply);
        replyEntity.setTopic(byId.get());
        replyRepository.save(replyEntity);
        return new ReplyDto();
    }

}
