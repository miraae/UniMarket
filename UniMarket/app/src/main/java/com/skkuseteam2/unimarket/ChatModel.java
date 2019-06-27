package com.skkuseteam2.unimarket;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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


    public ChatModel(int boardid,String name ,String text, int picture , int otherid) {

        //시간은 이부분에서 처리.
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());

        this.name = name;
        this.boardid = boardid;
        this.timelog = strDate;
        this.text = text;
        this.picture = picture;
        this.otherid = otherid;
    }

}
