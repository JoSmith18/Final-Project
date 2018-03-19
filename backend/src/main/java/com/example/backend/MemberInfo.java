package com.example.backend;

public class MemberInfo {
    public Member member;
    public Preferences preferences;

    public MemberInfo(Member member, Preferences preferences){
        this.member = member;
        this.preferences = preferences;
    }
}
