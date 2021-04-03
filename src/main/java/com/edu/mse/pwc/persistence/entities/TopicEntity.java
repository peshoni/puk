package com.edu.mse.pwc.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "topics")
public class TopicEntity extends BaseEntity {

    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<ReplyEntity> reply;

}
