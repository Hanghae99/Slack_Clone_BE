package com.example.springdocumentsocket.repository;

import com.example.springdocumentsocket.model.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

}
