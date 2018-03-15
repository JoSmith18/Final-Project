package com.example.backend;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
public class SignUpController {
    @PostMapping("/SignUp")
    public member signUp(@RequestBody SignUp newMem) {
        System.out.println(newMem.password);
            String hashedPassword = BCrypt.hashpw(newMem.password, BCrypt.gensalt());
            member newMember = memberRepository.insertMember(newMem.memberName, newMem.age
                    ,newMem.email, hashedPassword);
            if (newMember != null) {
                return newMember;
            } else {
                System.out.println("JSON IS WRONG JO'TAVIOUS");
                return null;
            }
    }

}
