package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class preferencesRepository {
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
}
