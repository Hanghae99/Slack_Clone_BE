package com.example.springdocumentsocket.service;

import com.example.springdocumentsocket.dto.MessageRequestDto;
import com.example.springdocumentsocket.model.User;
import com.example.springdocumentsocket.model.chat.ChatMessage;
import com.example.springdocumentsocket.model.chat.ChatRoom;
import com.example.springdocumentsocket.repository.ChatMessageRepository;
import com.example.springdocumentsocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    public void saveMessage(MessageRequestDto message, User user) {

        // 채팅방 가져오기
        ChatRoom chatRoom = chatRoomRepository.findByChatRoomId(message.getRoomId()).get();

        ChatMessage chatMessage =new ChatMessage(message, user,chatRoom);

    }
}
