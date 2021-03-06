package com.edu.mse.pwc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDto {
    private Long id;
    private String title;
    private Date createdAt;
    private Date modifiedAt;
    private Long userId;
    private UserDto user;
    private Long modifiedBy;
    private List<ReplyDto> repliesPage;
    private int seenCounter;
}
