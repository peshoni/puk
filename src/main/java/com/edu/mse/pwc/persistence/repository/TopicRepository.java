package com.edu.mse.pwc.persistence.repository;

import com.edu.mse.pwc.persistence.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

}
