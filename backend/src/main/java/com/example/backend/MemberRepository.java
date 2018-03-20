package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberRepository {

    public static ArrayList<Member> allMembers(){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM members");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Member> allMember = new ArrayList<Member>();
            while (resultSet.next()){
                allMember.add(new
                        Member(resultSet.getInt("id"),
                        resultSet.getString("memberName"),
                        resultSet.getString("age"),
                        resultSet.getInt("matchingPoints"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender")));
            }
            return allMember;
        }
        catch (SQLException e){
            return null;
        }
    }

    public static Member insertMember(String memberName, String age, String email, String password, String gender){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO members (" +
                            "memberName,age,email,password, matchingPoints, gender) " +
                            "VALUES (?,?,?,?,2,?)" +
                            "RETURNING id,matchingPoints");
            preparedStatement.setString(1,memberName);
            preparedStatement.setString(2,age);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,gender);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Member(resultSet.getInt("id"),memberName,
                    age, resultSet.getInt("matchingPoints") + 2,
                    email,password,gender);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Member> membersByGender(String gender){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM members WHERE gender = ?");
            preparedStatement.setString(1,gender);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Member> allMember = new ArrayList<Member>();
            while (resultSet.next()){
                allMember.add(new
                        Member(resultSet.getInt("id"),
                        resultSet.getString("memberName"),
                        resultSet.getString("age"),
                        resultSet.getInt("matchingPoints"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender")));
            }
            return allMember;
        }
        catch (SQLException e){
            return null;
        }
    }
}