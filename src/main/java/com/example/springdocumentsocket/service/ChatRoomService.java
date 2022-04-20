package com.example.springdocumentsocket.service;

import com.example.springdocumentsocket.model.chat.ChatRoom;
import com.example.springdocumentsocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoom createChatRoom = new ChatRoom(chatRoom);

        return  chatRoomRepository.save(createChatRoom);
    }

    public List<ChatRoom> chatRoomList() {

        return chatRoomRepository.findAll();
    }
}
