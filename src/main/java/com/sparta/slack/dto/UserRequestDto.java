package com.sparta.slack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String userEmail;
    private String password;
    private String passwordCheck;
    private String userName;

    public UserRequestDto (){
    }

    public UserRequestDto(String userEmail, String password, String passwordCheck, String userName) {
        this.userEmail = userEmail;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.userName = userName;
    }
}
