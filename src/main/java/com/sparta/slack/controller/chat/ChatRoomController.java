package com.sparta.slack.controller.chat;

import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

//    @GetMapping("/chat/{roomId}")
//    public String getChat(){
//        return "chat";
//    }
//
//    @GetMapping("/chatRoom")
//    public  String getChatRoom(){
//        return "createChatRoom";
//    }

//    // 채팅방 생성(local_client_Test)
//    @PostMapping("/chatroom/create")
//    public  String createChatRoom(@ModelAttribute ChatRoom chatRoom, ModelAndView mv){
//        System.out.println("생성된 채팅방 이름  : " + chatRoom.getChatRoomName());
//
//        chatRoomService.createChatRoom(chatRoom);
//        return "redirect:/chatRoom";
//    }

    // 채팅방 생성 (react_local:3000)
    @PostMapping("/chatRoom/create")
    public ChatRoom createChatRoom(@RequestBody  ChatRoom chatRoom){
        System.out.println("생성된 채팅방 이름  : " + chatRoom.getChatRoomName());
        return chatRoomService.createChatRoom(chatRoom);
    }
    // 전체 채팅방 가져오기
    @GetMapping("/chatRoom/get")
    public  List<ChatRoom> chatRoomList(){
        System.out.println("======================================");
        System.out.println("전체 채팅방 가져오기");
        System.out.println(chatRoomService.chatRoomList());
        System.out.println("======================================");
        return chatRoomService.chatRoomList();
    }


}
