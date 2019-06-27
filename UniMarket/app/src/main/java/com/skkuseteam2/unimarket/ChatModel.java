package com.skkuseteam2.unimarket;

public class ChatModel {

    public int boardid;
    public String name;
    public String timelog;
    public String text;
    public int  picture;
    public int otherid;

    public ChatModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }


    public ChatModel(int boardid,String name ,String timelog,String text, int picture , int otherid) {
        this.name = name;
        this.boardid = boardid;
        this.timelog = timelog;
        this.text = text;
        this.picture = picture;
        this.otherid = otherid;
    }

}
