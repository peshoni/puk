package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.exceptions.DuplicateTopicException;
import com.edu.mse.pwc.exceptions.ReplyNotFoundException;
import com.edu.mse.pwc.exceptions.TopicNotFoundException;
import com.edu.mse.pwc.mappers.TopicMapper;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
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
public class TopicService {
    private final UserService userService;
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicDto createTopic(TopicDto topic) throws DuplicateTopicException {
        try {
            TopicEntity topicEntity = topicMapper.topicDtoToEntity(topic);
            topicEntity.setUser(userService.getUserEntity(topic.getUserId()));
            TopicEntity savedTopic = topicRepository.save(topicEntity);
            return topicMapper.topicEntityToDto(savedTopic);
        } catch (Exception e) {
            throw new DuplicateTopicException("Duplicated topic.", e);
        }
    }

    public TopicDto getTopicDto(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            TopicEntity topicEntity = byId.get();
            return topicMapper.topicEntityToDto(topicEntity);
        }
        throw new TopicNotFoundException("No topic with id " + id + " was found");
    }

    public TopicEntity getTopicEntity(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new TopicNotFoundException("No topic with id " + id + " was found");
    }

    public List<TopicDto> getAllTopics() {
        List<TopicDto> list = topicRepository.findAll().stream().map(topicMapper::topicEntityToDto).collect(Collectors.toList());
        list.forEach(t -> {
            P.clearUserSensitiveData(t.getUser());
        });
        return list;
    }

    public ApiResponse<List<TopicDto>> getPageWithTopics(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<TopicEntity> pageResult = topicRepository.findAll(paging);
        if (pageResult.hasContent()) {
            List<TopicDto> page = pageResult.getContent().stream().map(topicMapper::topicEntityToDto).collect(Collectors.toList());
            page.forEach(t -> P.clearUserSensitiveData(t.getUser()));
            return new ApiResponse<List<TopicDto>>(HttpStatus.OK.value(), "Fetched successfully",
                    page, pageResult.getTotalElements());
        } else {
            return new ApiResponse<List<TopicDto>>(HttpStatus.OK.value(), "Empty",
                    new ArrayList<TopicDto>());
        }
    }

    public TopicDto update(TopicDto topic) {

        Optional<TopicEntity> byId = topicRepository.findById(topic.getId());
        if (!byId.isPresent()) {
            throw new ReplyNotFoundException("There is no topic with id " + topic.getId());
        }
        TopicEntity topicEntity = byId.get();
        topicEntity.setTitle(topic.getTitle());
        topicEntity.setModifiedBy(topic.getModifiedBy());

        TopicEntity updated = topicRepository.save(topicEntity);
        return topicMapper.topicEntityToDto(updated);
    }

}
