package com.sparta.slack.model.chat;


import com.sparta.slack.dto.chat.MessageRequestDto;
import com.sparta.slack.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ChatRoomJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joinId;
    @ManyToOne
    private ChatRoom ChatRoom;
    @ManyToOne
    private User user;
    @Column
    private String enterTime;

    public ChatRoomJoin(User user, MessageRequestDto message, ChatRoom chatRoom) {
        this.ChatRoom=chatRoom;
        this.user=user;
        this.enterTime = message.getCreatedAt();
    }

}
