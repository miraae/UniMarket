package com.skkuseteam2.unimarket;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatModel {

    private int boardid;
    private String name;
    private String timelog;
    private String text;
    private int  picture;
    private int otherid;

    public ChatModel() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public ChatModel(int boardid,String name ,String text, int picture , int otherid) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.name = name;
        this.boardid = boardid;
        this.timelog =   sdf.format(c.getTime());
        this.text = text;
        this.picture = picture;
        this.otherid = otherid;
    }

    public int getBoardid() {
        return boardid;
    }

    public String getName() {
        return name;
    }

    public String getTimelog() {
        return timelog;
    }

    public String getText() {
        return text;
    }

    public int getPicture() {
        return picture;
    }

    public int getOtherid() {
        return otherid;
    }
}
