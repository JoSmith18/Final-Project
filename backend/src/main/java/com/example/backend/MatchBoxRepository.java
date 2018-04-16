package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatchBoxRepository {
    public static MatchBox insertIntoMatchBox(Integer senderid, Integer receiverid, String message){
        try {
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO matchBox (senderID,receiverID,message) VALUES (?,?,?) RETURNING *");
            preparedStatement.setInt(1,senderid);
            preparedStatement.setInt(2,receiverid);
            preparedStatement.setString(3,message);
            ResultSet resultSet = preparedStatement.executeQuery();

            return new MatchBox(resultSet.getInt("id"),
                    resultSet.getInt("senderID"),
                    resultSet.getInt("receiverID"),
                    resultSet.getString("message"),
                    resultSet.getString("postedAt"));
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<MatchBox> getConversation(Integer memberLoggedInId, Integer otherMemberId){
        try {
            ArrayList<MatchBox> convo = new ArrayList<>();
            Connection conn = GetConnect.get();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM matchBox WHERE senderid = ? " +
                    "or senderid = ? " +
                    "and receiverid = ? " +
                    "or receiverid = ? " +
                    "order by postedat asc");
            preparedStatement.setInt(1,memberLoggedInId);
            preparedStatement.setInt(2,otherMemberId);
            preparedStatement.setInt(3,memberLoggedInId);
            preparedStatement.setInt(4,otherMemberId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                convo.add(new MatchBox(resultSet.getInt("id"),
                        resultSet.getInt("senderID"),
                        resultSet.getInt("receiverID"),
                        resultSet.getString("message"),
                        resultSet.getString("postedAt")));
            }
            return convo;
        }
        catch (SQLException e){
        System.out.println(e.getMessage());
        return null;
        }
    }
}
