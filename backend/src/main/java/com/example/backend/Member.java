package com.example.backend;

public class Member {
    public Integer id;
    public String memberName;
    public String age;
    public String githubLink;
    public String password;
    public String gender;
    public String sessionKey;

    public Member(Integer id, String memberName, String age,String githubLink, String password,String gender, String sessionKey){
        this.id = id;
        this.memberName = memberName;
        this.age = age;
        this.githubLink = githubLink;
        this.password = password;
        this.gender = gender;
        this.sessionKey = sessionKey;
    }
}
