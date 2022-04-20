package com.sparta.slack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
    private String userEmail;
    private String userName;
    private String fileUrl;
    private String fileName;

    public ImageDto(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
