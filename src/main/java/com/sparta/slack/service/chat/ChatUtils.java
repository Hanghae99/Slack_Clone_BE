package com.sparta.slack.service.chat;


import com.sparta.slack.model.User;
import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.repository.UserRepository;
import com.sparta.slack.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@RequiredArgsConstructor
@Service
public class ChatUtils {

    public final ChatRoomRepository chatRoomRepository;
    public final UserRepository userRepository;


    // 채팅방 가져오기
    public  ChatRoom selectOneChatRoom(String roomId){
        return chatRoomRepository.findByChatRoomId(roomId).orElseThrow(
                ()-> new NullPointerException("해당 채팅방이 존재하지 않습니다.")
        );
    }

    // 유저 가져오기
    public User selectOneUser(String username){
        return userRepository.findByUserName(username).orElseThrow(
                ()-> new NullPointerException("해당 회원이 존재하지 않습니다.")
        );
    }
    // 메시지 생성 시간 계산
    public String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return sdf.format(date);
    }
}
