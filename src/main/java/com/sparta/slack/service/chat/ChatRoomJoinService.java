package com.sparta.slack.service.chat;

import com.sparta.slack.dto.chat.MessageRequestDto;
import com.sparta.slack.model.User;
import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.model.chat.ChatRoomJoin;
import com.sparta.slack.repository.chat.ChatRoomJoinRepository;
import com.sparta.slack.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatRoomJoinService {
    private final ChatRoomJoinRepository chatRoomJoinRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatUtils chatUtils;

    // 유저가 재입장인지 입장인지 판별
    public Optional<ChatRoomJoin> userEnterChk(MessageRequestDto message) {

        // 해당 방 가져오기
        ChatRoom chatRoom = chatUtils.selectOneChatRoom(message.getRoomId());
        // 유저 가져오기
        User user = chatUtils.selectOneUser(message.getUsername());
        // 해당 유저가 이미 입장했을때 확인
       return chatRoomJoinRepository.findByChatRoomAndUser(chatRoom,user);

    }
    public void saveEnterTime(MessageRequestDto message) {
        // 해당 방 가져오기
        ChatRoom chatRoom = chatUtils.selectOneChatRoom(message.getRoomId());
        // 유저 가져오기
        User user = chatUtils.selectOneUser(message.getUsername());
        // enterTime 저장
        message.setCreatedAt(chatUtils.getCurrentTime());

        ChatRoomJoin chatRoomJoin =new ChatRoomJoin(chatRoom, user, message);

        chatRoomJoinRepository.save(chatRoomJoin);

    }
}
