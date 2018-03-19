package com.example.backend;

import org.springframework.web.bind.annotation.*;

@RestController
public class getPreferencesController {
    @PostMapping("/submitPrefs")
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    public MemberInfo submitPrefs(@RequestBody Answers answers) {
        System.out.println("HELLO");
        Preferences newPrefs = preferencesRepository.insertPreferences(answers.ID, answers.answer1, answers.answer2, answers.answer3,
                answers.answer4, answers.answer5, answers.answer6, answers.answer7, answers.answer8);
        if (newPrefs != null) {
            for (Member m : MemberRepository.allMembers()
                    ) {
                if (m.id == newPrefs.memID) {
                    return new MemberInfo(m, newPrefs);
                } else {
                    System.out.println("JSON IS WRONG JO'TAVIOUS");
                    return null;
                }
            }
        }
        else {
            System.out.println("JSON IS WRONG JO'TAVIOUS");
            return null;
        }
    return null;
    }
}
