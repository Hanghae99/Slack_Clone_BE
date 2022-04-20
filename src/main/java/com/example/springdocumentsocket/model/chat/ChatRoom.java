package com.example.springdocumentsocket.model.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ChatRoom {
    @Id
    private String chatRoomId;
    @Column
    private String chatRoomName;

    public ChatRoom(ChatRoom chatRoom) {
        this.chatRoomId = UUID.randomUUID().toString();
        this.chatRoomName= chatRoom.getChatRoomName();
    }


}
