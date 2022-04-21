package com.sparta.slack.controller;

import com.sparta.slack.dto.UserRequestDto;
import com.sparta.slack.dto.chat.MessageInviteUserResponseDto;
import com.sparta.slack.model.User;
import com.sparta.slack.security.UserDetailsImpl;
import com.sparta.slack.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/user/signup")
    public User createUser(@RequestBody UserRequestDto requestDto) {

        return userService.registerUser(requestDto);
    }

    // 예외 처리
    @ExceptionHandler({IllegalArgumentException.class})
    public Map<String, String> handleException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("errMsg", e.getMessage());
        return map;
    }

    // 현재 유저정보 조회
    @GetMapping("/userinfo")
    public User getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null){
            log.info("userDetail = {}", userDetails.getUser().getUserEmail());
            return userDetails.getUser();
        }else{
            log.info("userDetail = {}", userDetails.getUser().getUserEmail());
            return new User();
        }
    }
    // 전체 유저 정보 조회
    @GetMapping("/invite/userList")
    public List<MessageInviteUserResponseDto> inviteUserList(){
        return userService.inviteUserList();
    }
}
