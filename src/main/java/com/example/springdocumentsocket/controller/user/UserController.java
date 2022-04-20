package com.example.springdocumentsocket.controller.user;

import com.example.springdocumentsocket.dto.UserRequestDto;
import com.example.springdocumentsocket.model.User;
import com.example.springdocumentsocket.security.UserDetailsImpl;
import com.example.springdocumentsocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("asd")
    public User userinfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.userinfo(userDetails.getUser().getUserId());
    }
}
