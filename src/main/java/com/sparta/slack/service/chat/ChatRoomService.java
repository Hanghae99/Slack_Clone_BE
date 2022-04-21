package com.sparta.slack.service.chat;

import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.model.chat.ChatRoomJoin;
import com.sparta.slack.repository.chat.ChatRoomJoinRepository;
import com.sparta.slack.repository.chat.ChatRoomRepository;
import com.sparta.slack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomJoinRepository chatRoomJoinRepository;

    // 채팅방 생성
    public ChatRoom createChatRoom(ChatRoom chatRoom) {

        ChatRoom createChatRoom = new ChatRoom(chatRoom);
        return  chatRoomRepository.save(createChatRoom);
    }


    // 전체 채팅방 가져오기
    public List<ChatRoom> chatRoomList() {

        return chatRoomRepository.findAll();
    }


//    // 내가 들어있는 채팅방만 가져오기
//    public List<ChatRoom> chatRoomList(UserDetailsImpl userDetails) {
//        List<ChatRoomJoin> chatRoomJoinList =chatRoomJoinRepository.findAllByUser(userDetails.getUser());
//        List<ChatRoom> chatRoomList =new ArrayList<>();
//        for(ChatRoomJoin room : chatRoomJoinList){
//            ChatRoom chatRoom = new ChatRoom(room.getChatRoom().getChatRoomId(),room.getChatRoom().getChatRoomName());
//            chatRoomList.add(chatRoom);
//        }
//
//       return chatRoomList;
//    }

}
