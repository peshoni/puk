package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.mappers.TopicMapper;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicDto createTopic(TopicDto topic) {
        TopicEntity topicEntity = topicMapper.topicDtoToEntity(topic);
        TopicEntity savedTopic = topicRepository.save(topicEntity);
        return topicMapper.topicEntityToDto(savedTopic);
    }

    public TopicDto getTopic(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            TopicEntity topicEntity = byId.get();
            return topicMapper.topicEntityToDto(topicEntity);
        }
        throw new IllegalArgumentException("No such topic");
    }

    public List<TopicDto> getAllTopics() {
        return topicRepository.findAll().stream().map(t -> topicMapper.topicEntityToDto(t)).collect(Collectors.toList());
    }
}
