package com.example.backend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class notifyMatchController {

    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/notifyMatch/{id}")
    public void notifyMatch(@PathVariable Integer id, @RequestParam String phoneNumber){
        new SmsSender().sendNotification(MemberRepository.memberById(id), phoneNumber);
    }
}
