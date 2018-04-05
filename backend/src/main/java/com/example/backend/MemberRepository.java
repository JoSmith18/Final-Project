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
                        resultSet.getString("phoneNumber"),
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

    public static Member insertMember(String memberName, String age, String phoneNumber, String githubLink, String password, String gender,String sessionKey){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO members (" +
                            "memberName,age,githubLink,password, gender, sessionKey) " +
                            "VALUES (?,?,?,?,?,?)" +
                            "RETURNING id");
            preparedStatement.setString(1,memberName);
            preparedStatement.setString(2,age);
            preparedStatement.setString(3,githubLink);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,gender);
            preparedStatement.setString(6,sessionKey);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            conn.close();
            return new Member(resultSet.getInt("id"),memberName,
                    age, phoneNumber,
                    githubLink,password,gender,sessionKey);
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
                        resultSet.getString("phoneNumber"),
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

    public static Member deleteSessionKey(Integer id){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE members SET sessionKey = null WHERE id = ? RETURNING *");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            conn.close();
            return new
                    Member(resultSet.getInt("id"),
                    resultSet.getString("memberName"),
                    resultSet.getString("age"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("githubLink"),
                    resultSet.getString("password"),
                    resultSet.getString("gender"),
                    resultSet.getString("sessionKey"));
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Member isMember( String sessionKey,String memberName, String password){

        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE members SET sessionKey = ? WHERE memberName = ? and password = ? returning *"
            );
            preparedStatement.setString(1,sessionKey);
            preparedStatement.setString(2,memberName);
            preparedStatement.setString(3,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            conn.close();
            return new Member(resultSet.getInt("id"),
                    memberName,
                    resultSet.getString("age"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("githubLink"),
                    password,
                    resultSet.getString("gender"),
                    sessionKey
                    );

        }
        catch (SQLException e){
            return null;
        }
    }

    public static boolean deleteMember(Integer id){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM members WHERE id = ?"
            );
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            conn.close();
            return true;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Member memberById(Integer id){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM members WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            conn.close();
            return new Member(resultSet.getInt("id"),
                    resultSet.getString("memberName"),
                    resultSet.getString("age"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("githubLink"),
                    resultSet.getString("password"),
                    resultSet.getString("gender"),
                    resultSet.getString("sessionKey")
            );
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}