package com.example.backend;

public class MatchBox {
    public Integer id;
    public Integer senderID;
    public Integer receiverID;
    public String message;
    public String timeStamp;

    public MatchBox(Integer id, Integer senderID, Integer receiverID, String message, String timeStamp){
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
