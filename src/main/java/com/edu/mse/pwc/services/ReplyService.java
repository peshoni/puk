package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.exceptions.ReplyNotFoundException;
import com.edu.mse.pwc.mappers.ReplyMapper;
import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.persistence.repository.ReplyRepository;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final UserService userService;
    private final TopicService topicService;
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    public ReplyDto createReply(ReplyDto reply) {
        Long topicId = reply.getTopicId();
        Long userId = reply.getUserId();
        TopicEntity topicEntity = topicService.getTopicEntity(topicId);
        ReplyEntity replyEntity = replyMapper.replyDtoToEntity(reply);
        replyEntity.setTopic(topicEntity);
        UserEntity userEntity = userService.getUserEntity(userId);
        replyEntity.setUser(userEntity);
        ReplyEntity newReplyEntity = replyRepository.save(replyEntity);
        return replyMapper.replyEntityToDto(newReplyEntity);
    }

    public ApiResponse<TopicDto> getPageWithRepliesForTopic(Long topicId, int pageNumber, int pageSize) {
        TopicDto topic = topicService.getTopicDto(topicId);
        P.clearUserSensitiveData(topic.getUser());
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<ReplyEntity> pageResult = replyRepository.findByTopicId(topicId, paging);

        if (pageResult.hasContent()) {
            List<ReplyDto> pagedReplies = pageResult.getContent().stream().map(replyMapper::replyEntityToDto).collect(Collectors.toList());
            pagedReplies.forEach(r -> {
                P.clearUserSensitiveData(r.getUser());
            });
            topic.setRepliesPage(pagedReplies);
            return new ApiResponse<TopicDto>(HttpStatus.OK.value(), "Fetched successfully",
                    topic, pageResult.getTotalElements());
        } else {
            return new ApiResponse<TopicDto>(HttpStatus.OK.value(), "Empty",
                    new TopicDto());
        }
    }


    public ReplyDto updateReply(ReplyDto reply) {
        Optional<ReplyEntity> byId = replyRepository.findById(reply.getId());
        if (!byId.isPresent()) {
            throw new ReplyNotFoundException("There is no reply with id " + reply.getId());
        }
        ReplyEntity replyEntity = byId.get();
        replyEntity.setText(reply.getText());
        replyEntity.setModifiedBy(reply.getModifiedBy());

        ReplyEntity updated = replyRepository.save(replyEntity);
        return replyMapper.replyEntityToDto(updated);
    }

}
