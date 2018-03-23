package com.example.backend;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

public class LoginController {
    @Value("${app.salt}")
    private String salt;
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/Login")
    public Member login(@RequestBody Login isUser){
        String hashedPassword = BCrypt.hashpw(isUser.password,salt);
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String sessionKey = "";
        Random random = new Random();
        int randomLen = 12+random.nextInt(9);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            sessionKey+=c;
        }
        Member newMember = MemberRepository.isMember(sessionKey, isUser.memberName, hashedPassword);
        if (newMember != null) {
            return newMember;
        } else {
            System.out.println("JSON IS WRONG JO'TAVIOUS");
            return null;
        }
    }
}
