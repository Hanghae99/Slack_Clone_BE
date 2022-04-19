package com.sparta.slack.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.slack.dto.ImageDto;
import com.sparta.slack.dto.ImageRequestDto;
import com.sparta.slack.model.Image;
import com.sparta.slack.model.User;
import com.sparta.slack.repository.ImageRepository;
import com.sparta.slack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3Client amazonS3Client;
    private final UserRepository userRepository;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // Image 업로드
    @Transactional
    public void upload(ImageRequestDto imageRequestDto, String dirName) throws IOException {

        File uploadFile = convert(imageRequestDto.getFile())  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("잘못된 파일 형식입니다."));

        ImageDto imageDto = upload(uploadFile, dirName);

        Optional<User> user1 = userRepository.findByUserEmail(imageRequestDto.getUserEmail());

        User user = user1.get();

        user.setImgUrl(imageDto.getFileUrl());
        user.setUserName(imageRequestDto.getUserName());

        userRepository.save(user);
    }

    // S3로 파일 업로드하기
    private ImageDto upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();  // S3에 저장된 파일 이름
        System.out.println(fileName);
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        System.out.println(uploadImageUrl);
        removeNewFile(uploadFile);
        return new ImageDto(fileName, uploadImageUrl);
    }


    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
//        System.out.println(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
