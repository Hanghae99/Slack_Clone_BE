package com.example.springdocumentsocket.service;

import com.example.springdocumentsocket.dto.MessageRequestDto;
import com.example.springdocumentsocket.model.User;
import com.example.springdocumentsocket.model.chat.ChatRoom;
import com.example.springdocumentsocket.model.chat.ChatRoomJoin;
import com.example.springdocumentsocket.repository.ChatRoomJoinRepository;
import com.example.springdocumentsocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatRoomJoinService {
    private final ChatRoomJoinRepository chatRoomJoinRepository;
    private final ChatRoomRepository chatRoomRepository;

    public void joinTimeSave(User user, MessageRequestDto message) {
        Optional<ChatRoom> chatRoom= chatRoomRepository.findByChatRoomId(message.getRoomId());
        ChatRoomJoin chatRoomJoin =new ChatRoomJoin(user ,message,chatRoom.get());
        chatRoomJoinRepository.save(chatRoomJoin);

    }
}
