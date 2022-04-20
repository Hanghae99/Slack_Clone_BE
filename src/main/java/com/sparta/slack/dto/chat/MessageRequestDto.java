package com.sparta.slack.dto.chat;

import com.sparta.slack.model.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {
    private String username;
    private String roomId;
    private String message;
    private String createdAt;
    private ChatMessage.MessageType type;
}
