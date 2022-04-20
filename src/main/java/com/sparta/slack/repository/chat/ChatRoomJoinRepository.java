package com.sparta.slack.repository.chat;

import com.sparta.slack.model.User;
import com.sparta.slack.model.chat.ChatRoom;
import com.sparta.slack.model.chat.ChatRoomJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin,Long> {
    //Optional<User> findByChatRoomAndUser(ChatRoom chatRoom, User user);


}
