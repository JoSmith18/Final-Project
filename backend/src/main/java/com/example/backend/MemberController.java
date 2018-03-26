package com.example.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
public class MemberController {
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/delete/{id}")
    public boolean deleteMember(@PathVariable Integer id ){
        System.out.println("I made it here");
        return MemberRepository.deleteMember(id);
    }

    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/update/{id}")
    public static Preferences updatePreferences(@PathVariable Integer id, @RequestBody Preferences newPrefs){
        System.out.println(newPrefs);
       return PreferencesRepository.updatePreferences(id,newPrefs.answer1,
                newPrefs.answer2,
                newPrefs.answer3,newPrefs.answer4,
                newPrefs.answer5,newPrefs.answer6,
                newPrefs.answer7,newPrefs.answer8);

    }
}
