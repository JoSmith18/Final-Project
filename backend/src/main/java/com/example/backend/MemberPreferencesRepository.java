package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberPreferencesRepository {
    public static ArrayList<Member> membersThatMatch(Member user){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM memberPreferences,members WHERE memberId != ? and members.gender = ?");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Member> allMember = new ArrayList<Member>();
            while (resultSet.next()){
                allMember.add(new
                        Member(resultSet.getInt("id"),
                        resultSet.getString("memberName"),
                        resultSet.getString("age"),
                        resultSet.getString("githubLink"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("sessionKey")));
            }
            conn.close();
            return allMember;
        }
        catch (SQLException e){
            return null;
        }
    }
}
