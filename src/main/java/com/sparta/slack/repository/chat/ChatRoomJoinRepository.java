package com.sparta.slack.repository.chat;

import com.sparta.slack.model.chat.ChatRoomJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin,Long> {


}
