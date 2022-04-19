package com.sparta.slack.model;

import com.sparta.slack.dto.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long imageId;

    @Column
    private String fileUrl;

    @Column
    private String fileName;


    public Image(ImageDto imageDto) {
        this.fileUrl = imageDto.getFileUrl();
        this.fileName = imageDto.getFileName();
    }
}
