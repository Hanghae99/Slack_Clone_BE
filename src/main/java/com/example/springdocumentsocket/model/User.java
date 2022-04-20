package com.example.springdocumentsocket.model;

import com.example.springdocumentsocket.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    public User(UserRequestDto requestDto) {
        this.userEmail = requestDto.getUserEmail();
        this.password = requestDto.getPassword();
        this.userName = requestDto.getUserName();
    }
}
