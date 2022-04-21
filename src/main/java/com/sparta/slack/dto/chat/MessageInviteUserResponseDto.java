package com.sparta.slack.dto.chat;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageInviteUserResponseDto {
    private String username;
    private String userEmail;
    private String imageUrl;



    public MessageInviteUserResponseDto(String userEmail, String userName, String imageUrl) {
        this.userEmail =userEmail;
        this.username = userName;
        this.imageUrl=imageUrl;
    }
}
