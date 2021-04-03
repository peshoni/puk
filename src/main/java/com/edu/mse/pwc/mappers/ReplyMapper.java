package com.edu.mse.pwc.mappers;

import com.edu.mse.pwc.dtos.ReplyDto;
import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReplyMapper {

    ReplyEntity replyDtoToEntity(ReplyDto dto);

    ReplyDto replyEntityToDto(ReplyEntity dto);

}
