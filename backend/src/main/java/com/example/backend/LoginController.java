package com.example.backend;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class LoginController {
    @Value("${app.salt}")
    private String salt;
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/Login")
    public Member login(@RequestBody Login isUser){
        String hashedPassword = BCrypt.hashpw(isUser.password,salt);
        String sessionKey = new SignUpController().createSessionKey();
        Member newMember = MemberRepository.isMember(sessionKey, isUser.memberName, hashedPassword);
        if (newMember != null) {
            return newMember;
        } else {
            System.out.println("JSON IS WRONG JO'TAVIOUS");
            return null;
        }
    }



}
