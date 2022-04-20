package com.sparta.slack.controller;

import com.sparta.slack.dto.UserRequestDto;
import com.sparta.slack.model.User;
import com.sparta.slack.security.UserDetailsImpl;
import com.sparta.slack.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/user")
    public User getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getUser(userDetails);
    }
}
