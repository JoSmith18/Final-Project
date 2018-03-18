package com.example.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class getPreferencesController {
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/submitPrefs/{id}")
    public preferences submitPrefs(@RequestBody Answers answers){

    }
}
