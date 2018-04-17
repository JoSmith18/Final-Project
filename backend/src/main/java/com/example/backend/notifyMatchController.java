package com.example.backend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class notifyMatchController {
    @CrossOrigin()
    @PostMapping("/notifyMatch/{id}/{receiverid}")
    public ArrayList<MatchBox> notifyMatch(@PathVariable Integer id, @PathVariable Integer receiverid){
        return MatchBoxRepository.getConversation(id,receiverid);

    }
}
