package com.edu.mse.pwc.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "topics")
public class TopicEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String title;
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<ReplyEntity> reply;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "modified_by")
    private Long modifiedBy;
}
