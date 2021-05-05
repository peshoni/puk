package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.exceptions.ReplyNotFoundException;
import com.edu.mse.pwc.exceptions.TopicNotFoundException;
import com.edu.mse.pwc.mappers.ReplyMapper;
import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.persistence.repository.ReplyRepository;
import com.edu.mse.pwc.persistence.repository.TopicRepository;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final UserService userService;
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final TopicRepository topicRepository;

    public ReplyDto createReply(ReplyDto reply) {
        P.syso(reply);
        Long topicId = reply.getTopicId();
        Long userId = reply.getUserId();

        TopicEntity topicEntity = getTopicEntity(topicId);

        ReplyEntity replyEntity = replyMapper.replyDtoToEntity(reply);
        replyEntity.setTopic(topicEntity);

        UserEntity userEntity = userService.getUserEntity(userId);
        replyEntity.setUser(userEntity);


        ReplyEntity newReplyEntity = replyRepository.save(replyEntity);

        return replyMapper.replyEntityToDto(newReplyEntity);
    }

    public ApiResponse<List<ReplyDto>> getPageWithRepliesForTopic(Long topicId, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<ReplyEntity> pageResult = replyRepository.findByTopicId(topicId, paging);
        P.syso(pageResult.getTotalElements());
        if (pageResult.hasContent()) {
            return new ApiResponse<List<ReplyDto>>(HttpStatus.OK.value(), "Fetched successfully",
                    pageResult.getContent().stream().map(replyMapper::replyEntityToDto).collect(Collectors.toList()), pageResult.getTotalElements());
        } else {
            return new ApiResponse<List<ReplyDto>>(HttpStatus.OK.value(), "Empty",
                    new ArrayList<ReplyDto>());
        }
    }

//    public List<ReplyDto> getRepliesForTopic(Long topicId) {
//        TopicEntity topic = getTopicEntity(topicId);
//        return topic
//                .getReply()
//                .stream()
//                .map(replyMapper::replyEntityToDto)
//                .collect(Collectors.toList());
//    }

    public List<ReplyDto> getRepliesForTopic(Long topicId) {
        TopicEntity topic = getTopicEntity(topicId);
        return topic
                .getReply()
                .stream()
                .map(replyMapper::replyEntityToDto)
                .collect(Collectors.toList());
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

    private TopicEntity getTopicEntity(Long topicId) {
        Optional<TopicEntity> byId = topicRepository.findById(topicId);
        if (!byId.isPresent()) {
            throw new TopicNotFoundException("No topic found with id " + topicId);
        }
        return byId.get();
    }
}
