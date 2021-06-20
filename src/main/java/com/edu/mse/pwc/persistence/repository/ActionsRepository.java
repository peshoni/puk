package com.edu.mse.pwc.persistence.repository;

import com.edu.mse.pwc.persistence.entities.ActionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionsRepository extends JpaRepository<ActionsEntity, Long> {
    ActionsEntity findByUserIdAndTopicId(long userId, long topicId);

    ActionsEntity findByUserId(long userId);
}
