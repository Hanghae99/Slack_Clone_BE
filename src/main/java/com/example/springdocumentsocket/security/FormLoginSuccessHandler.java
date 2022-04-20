package com.example.springdocumentsocket.security;

import com.example.springdocumentsocket.security.jwt.JwtTokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,String > hashMap = new HashMap<>();
        hashMap.put("nickName",userDetails.getUsername());
        String msg = new String (objectMapper.writeValueAsString(hashMap).getBytes("UTF-8"), "ISO-8859-1");
        response.getOutputStream()
                .println(msg);
    }
}