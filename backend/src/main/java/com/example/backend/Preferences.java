package com.example.backend;

public class Preferences {
    public Integer id;
    public Integer memID;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public String answer5;
    public String answer6;
    public String answer7;
    public String answer8;

    public Preferences() {}

    public Preferences(Integer id, Integer memID, String answer1, String answer2,
                       String answer3, String answer4, String answer5,
                       String answer6, String answer7, String answer8){
        this.id = id;
        this.memID = memID;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.answer7 = answer7;
        this.answer8 = answer8;
    }


}
