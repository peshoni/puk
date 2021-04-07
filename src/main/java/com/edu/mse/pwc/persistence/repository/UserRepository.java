package com.edu.mse.pwc.persistence.repository;

import com.edu.mse.pwc.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
