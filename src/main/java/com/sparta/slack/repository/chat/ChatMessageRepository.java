package com.sparta.slack.repository.chat;

import com.sparta.slack.model.chat.ChatMessage;
import com.sparta.slack.model.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findByChatroomAndMessageTypeOrderByCreatedAtAsc(ChatRoom chatRooms, ChatMessage.MessageType messageType);
}
