package com.example.backend;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@RestController
public class SignUpController {

    @Value("${app.salt}")
    private String salt;

    String createSessionKey() {
        String alphabet= "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*();[]{}\\|,./<>?`~-=_+";
        String sessionKey = "";
        Random random = new Random();
        int randomLen = 12+random.nextInt(9);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            sessionKey+=c;
        }
        return sessionKey;
    }

    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/SignUp")
    public Member signUp(@RequestBody SignUp newMem) {
        String hashedPassword = BCrypt.hashpw(newMem.password, salt);

        String sessionKey = createSessionKey();

        Member newMember = MemberRepository.insertMember(
                newMem.memberName,
                newMem.age,
                newMem.phoneNumber,
                newMem.githubLink,
                hashedPassword,
                newMem.gender,
                sessionKey);

        if (newMember != null) {
            return newMember;
        } else {
            System.out.println("JSON IS WRONG JO'TAVIOUS");
            return null;
        }
    }
}
