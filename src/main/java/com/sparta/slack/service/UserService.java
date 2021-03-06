package com.sparta.slack.service;

import com.sparta.slack.dto.UserRequestDto;
import com.sparta.slack.dto.chat.MessageInviteUserResponseDto;
import com.sparta.slack.model.User;
import com.sparta.slack.repository.UserRepository;
import com.sparta.slack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User registerUser(@RequestBody UserRequestDto requestDto) {
        String userEmail = requestDto.getUserEmail();
        String password = requestDto.getPassword();
        String userName = requestDto.getUserName();

        //회원가입 중복 체크
        Optional<User> found = userRepository.findByUserEmail(userEmail);
        Optional<User> found2 = userRepository.findByUserName(userName);

        //사용가능한 문자 정규화
        String pattern = "^[A-Za-z0-9#?!@$ .%^&*-]*$";
        //        ^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])*$
        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일 입니다!");
        } else if (found2.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        } else if (!Pattern.matches(pattern, userEmail)) {
            throw new IllegalArgumentException("영문, 숫자로만 입력하세요");
        } else if (!Pattern.matches(pattern,password)) {
            throw new IllegalArgumentException("비밀변호는 대소문자숫자특수문자를 포함해야합니다");
        } else if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호를 8자 이상 입력하세요");
        } else if (password.contains(userEmail)) {
            throw new IllegalArgumentException("비밀번호에 Email를 포함할 수 없습니다.");
        } else if (userName.length() > 6 || userName.length() < 2) {
            throw new IllegalArgumentException("닉네임은 2자~6자범위로 입력해주세요");
        } else if (userEmail.contains("<") || userEmail.contains(">") || userEmail.contains("script")) {
            throw new IllegalArgumentException("xss 안돼요 하지마세요 돌아가세요");
        } else if (userName.contains("<") || userName.contains(">") || userName.contains("script")) {
            throw new IllegalArgumentException("xss 안돼요 하지마세요 돌아가세요");
        }

        // 패스워드 인코딩
        password = passwordEncoder.encode(password);
        requestDto.setPassword(password);

        User user = new User(requestDto);

        return userRepository.save(user);
    }

    // 현재 유저 정보 가져오기
    public User getUser(UserDetailsImpl userDetails) {
        User user = new User();
        user.setUserEmail(userDetails.getUserEmail());
        user.setUserName(userDetails.getUsername());
        user.setImageUrl(userDetails.getUser().getImageUrl());
        return user;
    }

    // 전체 유저 목록 가져오기
    public List<MessageInviteUserResponseDto> inviteUserList() {
        List<User> userList =userRepository.findAll();
        List<MessageInviteUserResponseDto> inviteUserList = new ArrayList<>();
        for(User user : userList){
            MessageInviteUserResponseDto messageInviteUserResponseDto = new MessageInviteUserResponseDto(user.getUserEmail(),user.getUserName(),user.getImageUrl());
            inviteUserList.add(messageInviteUserResponseDto);
        }
        return inviteUserList;
    }
}
