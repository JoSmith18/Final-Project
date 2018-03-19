package com.example.backend;

public class Member {
    public Integer id;
    public String memberName;
    public String age;
    public Integer matchingPoints;
    public String email;
    public String password;
    public String profileURL;

    public Member(Integer id, String memberName, String age, Integer matchingPoints, String email, String password, String profileURL){
        this.id = id;
        this.memberName = memberName;
        this.age = age;
        this.matchingPoints = matchingPoints ;
        this.email = email;
        this.password = password;
        this.profileURL = profileURL;
    }
}
