package com.sparta.slack.service.chat;

import com.sparta.slack.dto.chat.MessageRequestDto;
import com.sparta.slack.model.User;
import com.sparta.slack.model.chat.ChatMessage;
import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.repository.chat.ChatMessageRepository;
import com.sparta.slack.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
