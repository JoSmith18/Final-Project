package com.example.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
public class MemberController {
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteMember(@PathVariable Integer id ){
        return MemberRepository.deleteMember(id);
    }
}
