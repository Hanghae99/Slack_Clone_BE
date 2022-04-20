package com.sparta.slack.service.chat;

import com.sparta.slack.dto.chat.MessageRequestDto;
import com.sparta.slack.model.User;
import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.model.chat.ChatRoomJoin;
import com.sparta.slack.repository.chat.ChatRoomJoinRepository;
import com.sparta.slack.repository.chat.ChatRoomRepository;
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