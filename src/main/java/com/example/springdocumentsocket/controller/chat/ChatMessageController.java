package com.example.springdocumentsocket.controller.chat;

import com.example.springdocumentsocket.dto.MessageRequestDto;
import com.example.springdocumentsocket.dto.MessageResponseDto;
import com.example.springdocumentsocket.model.User;
import com.example.springdocumentsocket.model.chat.ChatMessage;
import com.example.springdocumentsocket.model.chat.ChatRoomJoin;
import com.example.springdocumentsocket.repository.UserRepository;
import com.example.springdocumentsocket.security.jwt.JwtDecoder;
import com.example.springdocumentsocket.service.ChatMessageService;
import com.example.springdocumentsocket.service.ChatRoomJoinService;
import com.example.springdocumentsocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

// 해당 컨트롤러는 Message Handler라고 생각하면 됨
@RequiredArgsConstructor
@Controller
public class ChatMessageController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final JwtDecoder jwtDecoder;
    private final ChatMessageService chatMessageService;
    private final UserRepository userRepository;
    private final ChatRoomJoinService chatRoomJoinService;

    // stomp ws를 통해 해당 경로로 메세지가 들어왔을때 메시지의 "destination header"와 "messageMapping"에
    // 설정된 경로가 일치하는 "handler"를 찾고 처리
    // "configuration"에서 설정한 "app"이라는 "prifix"과 합쳐서 "app/hello"라는 "destination header"를 가진
    // 메세지들이 이 handler를 타게 된다.
    @MessageMapping("/hello")
    // handler에서 처리를한 반환값을 "/topic/greetings" 경로로 다시 반환
    // 앞에 "/topic"이 붙었으니 "simpleBroker"로 전달
   // @SendTo("/topic/greetings")
    public void greeting(@RequestBody MessageRequestDto message
         //   , @Header("token") String token
    ) throws Exception {
        System.out.println("chatHandler 에서 roomId : " + message.getRoomId());
        System.out.println("chatHandler 에서 message : " + message.getMessage());
        System.out.println("chatHandler 에서 userName : " + message.getUsername());


        /* ToDo
         * 해당 destination으로 전달할때 sender(user)를 같이 전달해야함
         */
        // 로그인 회원 정보를 들어온 메시지에 값 세팅
//        String username = jwtDecoder.decodeUsername(token);
//        Optional<User> user1 = userRepository.findByUserName(username);
//        User user = user1.get();
//        message.setUserId(user.getUserId());
//        message.setUsername(user.getUserName());
//
//        // 메시지 생성 시간 삽입
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
//        Calendar cal = Calendar.getInstance();
//        Date date = cal.getTime();
//        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//        String dateResult = sdf.format(date);
//        message.setCreatedAt(dateResult);
//        // 방에 처음입장했을때.
//        if(ChatMessage.MessageType.ENTER.equals(message.getType())){
//            // ChatRoomJoin 에 저장,
//            chatRoomJoinService.joinTimeSave(user,message);
//        }
        // 방을 구별해주기 위해서 @SendTo를 쓰지 않고 SimpMessageSendingOperations를 사용해서 방 구별을 해줄 수 있게 함 ex) "/topic/greetings+roomId"
        messagingTemplate.convertAndSend("/topic/greetings"+message.getRoomId(),new MessageResponseDto(HtmlUtils.htmlEscape(message.getMessage())));
        /*+message.getRoomId()*/
       // return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
       // 메세지 저장
      //  chatMessageService.saveMessage(message,user);
    }

}
