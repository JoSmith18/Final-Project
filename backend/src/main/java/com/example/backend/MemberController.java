package com.example.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
public class MemberController {
    @CrossOrigin()
    @PostMapping("/delete/{id}")
    public boolean deleteMember(@PathVariable Integer id ){
        return MemberRepository.deleteMember(id);
    }

    @CrossOrigin()
    @PostMapping("/update/{id}")
    public static Preferences updatePreferences(@PathVariable Integer id, @RequestBody Preferences newPrefs){
       return PreferencesRepository.updatePreferences(id,newPrefs.answer1,
                newPrefs.answer2,
                newPrefs.answer3,newPrefs.answer4,
                newPrefs.answer5,newPrefs.answer6,
                newPrefs.answer7,newPrefs.answer8);

    }
}
