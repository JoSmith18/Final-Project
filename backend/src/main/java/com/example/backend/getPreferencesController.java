package com.example.backend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class getPreferencesController {
    @PostMapping("/submitPrefs")
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    public Preferences submitPrefs(@RequestBody Answers answers) {
        Preferences newPrefs = PreferencesRepository.insertPreferences(answers.ID, answers.answer1, answers.answer2, answers.answer3,
                answers.answer4, answers.answer5, answers.answer6, answers.answer7, answers.answer8);
        if (newPrefs != null) {
            return newPrefs;
            }
        else {
            System.out.println("JSON IS WRONG JO'TAVIOUS");
            return null;
        }
    }

    @GetMapping("/memID/{id}")
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    public MemberInfo getFeed(@PathVariable Integer id){
        Member memInfo = null;
        Preferences preferencesInfo = null;
        for (Member m: MemberRepository.allMembers()
             ) {
            if (m.id == id){
                memInfo = m;
            }

        }
        for (Preferences p: PreferencesRepository.allPreferences()
                ){
            if (p.memID == id){
                preferencesInfo = p;
            }
        }
        return new MemberInfo(memInfo,preferencesInfo);
    }

    @GetMapping("/getMatches/{id}")
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    public ArrayList<MemberInfo> getMatches(@PathVariable Integer id){
        MemberInfo memInformation = getFeed(id);
        ArrayList<MemberInfo> matches = new ArrayList<MemberInfo>();
        ArrayList<Member> firstCut = MemberRepository.membersByGender(memInformation.preferences.answer2);
        for (Member m:firstCut
             ) {
            matches.add(getFeed(m.id));
        }
        return matches;
    }
}
