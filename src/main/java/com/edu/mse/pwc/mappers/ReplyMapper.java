package com.edu.mse.pwc.mappers;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReplyMapper {

    ReplyEntity replyDtoToEntity(ReplyDto dto);

    @Mapping(source = "topic.id", target = "topicId")
    ReplyDto replyEntityToDto(ReplyEntity dto);

}
