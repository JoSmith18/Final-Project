package com.example.backend;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class getPreferencesController {
    @PostMapping("/submitPrefs")
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    public preferences submitPrefs(@RequestBody Answers answers){
    System.out.println("HELLO");
    preferences newPrefs = preferencesRepository.insertPreferences(answers.ID,answers.answer1,answers.answer2,answers.answer3,
            answers.answer4,answers.answer5,answers.answer6,answers.answer7,answers.answer8);
    System.out.println("Made It This Far!!");
    if (newPrefs != null){
        return newPrefs;
    }
    else {
        System.out.println("JSON IS WRONG JO'TAVIOUS");
        return null;
    }
        }

}
