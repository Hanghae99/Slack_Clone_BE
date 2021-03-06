package com.sparta.slack.repository;

import com.sparta.slack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String username);

    Optional<User> findByUserName(String username);
}
