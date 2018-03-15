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
                        resultSet.getInt("age"),
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

    public static member insertMember(String memberName,Integer age, String email, String password){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO members (" +
                            "memberName,age,email,password) " +
                            "VALUES (?,?,?,?)");
            preparedStatement.setString(1,memberName);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new member(resultSet.getInt("id"),resultSet.getString("memberName"),
                    resultSet.getInt("age"), resultSet.getInt("matchingPoints"),
                    resultSet.getString("email"),resultSet.getString("password"));
        }
        catch (SQLException e){
            return null;
        }
    }
}