package com.sparta.slack.controller.chat;

import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.security.UserDetailsImpl;
import com.sparta.slack.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    // 채팅방 생성 (react_local:3000)
    @PostMapping("/chatRoom/create")
    public ChatRoom createChatRoom(@RequestBody  ChatRoom chatRoom){
        log.info("생성된 채팅방 이름 = {}", chatRoom.getChatRoomName());
        return chatRoomService.createChatRoom(chatRoom);
    }
    // 전체 채팅방 가져오기
    @GetMapping("/chatRoom/get")
    public  List<ChatRoom> chatRoomList(){
        log.info("전체 채팅방 정보 가져오기 = {}",chatRoomService.chatRoomList());
        return chatRoomService.chatRoomList();
    }

//    @PostMapping("invite/user")
//    public String inviteUser(){
//
//    }


//    // 내가 들어있는 채팅방만 가져오기
//    @GetMapping("/chatRoom/get")
//    public  List<ChatRoom> chatRoomList(@AuthenticationPrincipal UserDetailsImpl userDetails){
//
//        return chatRoomService.chatRoomList(userDetails);
//    }



}
