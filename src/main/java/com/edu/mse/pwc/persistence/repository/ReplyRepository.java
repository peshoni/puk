package com.edu.mse.pwc.persistence.repository;

import com.edu.mse.pwc.persistence.entities.ReplyEntity;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

}
