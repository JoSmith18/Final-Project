package com.example.backend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class notifyMatchController {

    @CrossOrigin()
    @PostMapping("/notifyMatch/{id}")
    public void notifyMatch(@PathVariable Integer id, @RequestBody String phoneNumber){

    }
}
