package com.sparta.slack.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageRequestDto {
    private String userEmail;
    private String userName;
    private MultipartFile file;

    public ImageRequestDto(String userEmail, String userName, MultipartFile file) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.file = file;
    }
}
