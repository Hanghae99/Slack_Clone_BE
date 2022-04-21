package com.sparta.slack.dto.chat;

import com.sparta.slack.model.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequestDto {
    private String username;
    private String roomId;
    private String message;
    private String createdAt;
    private ChatMessage.MessageType type;

    public MessageRequestDto(ChatMessage chatMessage) {
        this.username = chatMessage.getUser().getUserName();
        this.roomId = chatMessage.getChatroom().getChatRoomId();
        this.createdAt =chatMessage.getCreatedAt();
        this.type = chatMessage.getMessageType();
    }
}
