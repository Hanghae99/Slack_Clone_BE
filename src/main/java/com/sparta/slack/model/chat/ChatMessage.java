package com.sparta.slack.model.chat;

import com.sparta.slack.dto.chat.MessageRequestDto;
import com.sparta.slack.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Setter
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @ManyToOne
    private User user;
    @ManyToOne
    private ChatRoom Chatroom; // 방번호
    @Column
    private MessageType messageType;
    @Column
    private String message; // 메시지
    @Column
    private String createdAt;


    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK, QUIT
    }
    public ChatMessage(ChatRoom chatRoom, User user,MessageRequestDto message){
        this.user=user;
        this.Chatroom=chatRoom;
        this.message=message.getMessage();
        this.createdAt=message.getCreatedAt();
    }
}
