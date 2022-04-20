package com.example.springdocumentsocket.dto;

import com.example.springdocumentsocket.model.User;
import com.example.springdocumentsocket.model.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {
    private Long userId;
    private String username;
    private String roomId;
    private String message;
    private String createdAt;
    private ChatMessage.MessageType type;
}
