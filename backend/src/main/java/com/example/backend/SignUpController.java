package com.example.backend;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class SignUpController {
    @Value("${app.salt}")
    private String salt;
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/SignUp")
    public Member signUp(@RequestBody SignUp newMem) {
            String hashedPassword = BCrypt.hashpw(newMem.password, salt);
            Member newMember = MemberRepository.insertMember(newMem.memberName, newMem.age
                    ,newMem.email, hashedPassword,newMem.gender);
            if (newMember != null) {
                return newMember;
            } else {
                System.out.println("JSON IS WRONG JO'TAVIOUS");
                return null;
            }
    }

}
