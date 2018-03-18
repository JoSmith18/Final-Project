package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class memberRepository {

    public static ArrayList<member> allMembers(){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM members");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<member> allMember = new ArrayList<member>();
            while (resultSet.next()){
                allMember.add(new
                        member(resultSet.getInt("id"),
                        resultSet.getString("memberName"),
                        resultSet.getString("age"),
                        resultSet.getInt("matchingPoints"),
                        resultSet.getString("email"),
                        resultSet.getString("password")));
            }
            return allMember;
        }
        catch (SQLException e){
            return null;
        }
    }

    public static member insertMember(String memberName,String age, String email, String password){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO members (" +
                            "memberName,age,email,password, matchingPoints) " +
                            "VALUES (?,?,?,?,2)" +
                            "RETURNING id,matchingPoints");
            preparedStatement.setString(1,memberName);
            preparedStatement.setString(2,age);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new member(resultSet.getInt("id"),memberName,
                    age, resultSet.getInt("matchingPoints") + 2,
                    email,password);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}