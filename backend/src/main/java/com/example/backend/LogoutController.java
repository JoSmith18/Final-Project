package com.example.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    @CrossOrigin
    @PostMapping("/logout/{id}")
    public Boolean logout(@PathVariable Integer id){
        return (MemberRepository.deleteSessionKey(id) != null);
    }
}
