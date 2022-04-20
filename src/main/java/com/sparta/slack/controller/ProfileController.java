package com.sparta.slack.controller;

import com.sparta.slack.dto.ImageRequestDto;
import com.sparta.slack.security.UserDetailsImpl;
import com.sparta.slack.service.ImageService;
import com.sparta.slack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ProfileController {

    private final ImageService imageService;
    private final UserService userService;
    private Object userDetails;

    @Autowired
    public ProfileController(ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    //프로필 이미지 업로드
    @PutMapping("/api/userImage")
    public void upload(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("userName") String userName,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        //userEmail은 바뀌지 않는데 이 파라미터가 필요할까?
        ImageRequestDto imageRequestDto = new  ImageRequestDto(userDetails.getUser().getUserEmail(), userName, file);
        imageService.upload(imageRequestDto,"static");
    }
}
