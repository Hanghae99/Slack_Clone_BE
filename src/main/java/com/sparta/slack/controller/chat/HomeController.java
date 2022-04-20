package com.sparta.slack.controller.chat;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 후에 지워야하는거
@Controller
public class HomeController {


    @GetMapping("/user/loginView")
    public String userloginView(){

        return "loginView";
    }
}
