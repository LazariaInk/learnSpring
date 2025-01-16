package com.pluriverse.learnSpring.repository;

import com.pluriverse.learnSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
