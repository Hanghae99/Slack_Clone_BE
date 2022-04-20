package com.example.springdocumentsocket.repository;

import com.example.springdocumentsocket.model.chat.ChatRoomJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin,Long> {


}
