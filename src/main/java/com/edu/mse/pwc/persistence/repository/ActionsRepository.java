package com.edu.mse.pwc.persistence.repository;

import com.edu.mse.pwc.persistence.entities.ActionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActionsRepository extends JpaRepository<ActionsEntity, Long> {
    ActionsEntity findByUserIdAndTopicId(long userId, long topicId);

    @Query(value = "SELECT COUNT(*) FROM actions ac WHERE ac.topic_id = :topicId ", nativeQuery = true)
    int countUsersSawTheTopic(long topicId);
}
