package com.sparta.slack.dto.chat;

import com.sparta.slack.model.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter

@NoArgsConstructor
public class MessageResponseDto {
    private Long messageId;
    private String username;
    private String roomId;
    private String message;
    private String createdAt;
    private String imageUrl;
    private ChatMessage.MessageType type;

    public MessageResponseDto(
            Long messageId,
            String message,
            String createdAt ,
            String imageUrl,
            String username,
            String roomId,
            ChatMessage.MessageType messageType
    ) {
        this.messageId=messageId;
        this.message = message;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.username = username;
        this.roomId= roomId;
        this.type =messageType;
    }

    public MessageResponseDto(ChatMessage chatMessage) {
        this.messageId = chatMessage.getMessageId();
        this.message = chatMessage.getMessage();
        this.username = chatMessage.getUser().getUserName();
        this.roomId = chatMessage.getChatroom().getChatRoomId();
        this.createdAt =chatMessage.getCreatedAt();
        this.type = chatMessage.getMessageType();
        this.imageUrl = chatMessage.getUser().getImageUrl();
    }
}
