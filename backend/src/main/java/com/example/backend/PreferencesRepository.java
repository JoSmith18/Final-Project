package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreferencesRepository {
    public static ArrayList<Preferences> allPreferences(){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM preferences");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Preferences> allPreferences = new ArrayList<Preferences>();
            while (resultSet.next()){
                allPreferences.add(new
                        Preferences(resultSet.getInt("id"),
                        resultSet.getInt("memberId"),
                        resultSet.getString("answer1"),
                        resultSet.getString("answer2"),
                        resultSet.getString("answer3"),
                        resultSet.getString("answer4"),
                        resultSet.getString("answer5"),
                        resultSet.getString("answer6"),
                        resultSet.getString("answer7"),
                        resultSet.getString("answer8")));
            }
            return allPreferences;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Preferences insertPreferences(Integer memID, String answer1, String answer2,
                                                String answer3, String answer4, String answer5,
                                                String answer6, String answer7, String answer8){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO preferences (memberId,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8)"  +
                            "VALUES (?,?,?,?,?,?,?,?,?)" +
                            "RETURNING id");
            preparedStatement.setInt(1,memID);
            preparedStatement.setString(2,answer1);
            preparedStatement.setString(3,answer2);
            preparedStatement.setString(4,answer3);
            preparedStatement.setString(5,answer4);
            preparedStatement.setString(6,answer5);
            preparedStatement.setString(7,answer6);
            preparedStatement.setString(8,answer7);
            preparedStatement.setString(9,answer8);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Preferences(resultSet.getInt("id"),memID,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Preferences updatePreferences(Integer memID, String answer1, String answer2,
                                                String answer3, String answer4, String answer5,
                                                String answer6, String answer7, String answer8){
try {
        Connection conn = GetConnect.get();
        PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE preferences SET answer1 = ?,answer2 = ?,answer3 = ?,answer4 = ?,answer5 = ? ,answer6 = ? ,answer7 = ? ,answer8 = ? WHERE memberId = ?" +
                        "RETURNING *");
        preparedStatement.setString(1,answer1);
        preparedStatement.setString(2,answer2);
        preparedStatement.setString(3,answer3);
        preparedStatement.setString(4,answer4);
        preparedStatement.setString(5,answer5);
        preparedStatement.setString(6,answer6);
        preparedStatement.setString(7,answer7);
        preparedStatement.setString(8,answer8);
        preparedStatement.setInt(9,memID);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new Preferences(resultSet.getInt("id"),memID,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8);
    }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return  null;
        }
    }
}