package com.sparta.slack.service.chat;

import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.repository.chat.ChatRoomRepository;
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
