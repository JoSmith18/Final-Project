package com.example.backend;

public class member {
    public Integer id;
    public String memberName;
    public String age;
    public Integer matchingPoints;
    public String email;
    public String password;

    public member(Integer id, String memberName, String age, Integer matchingPoints, String email, String password){
        this.id = id;
        this.memberName = memberName;
        this.age = age;
        this.matchingPoints = matchingPoints;
        this.email = email;
        this.password = password;
    }
}
