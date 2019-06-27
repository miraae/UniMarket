package com.skkuseteam2.unimarket;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeModel {

    public String day;
    public String type;
    public String hour;
    public String min;
    public String daychecklist;

    public void TimeModel(Date mydate,String _daychecklist){
       int _hour = Integer.parseInt(GetDateTime(mydate,"HH"));
       //오후 오전 완성
       if(_hour > 12) {
           type = "오후";
           hour = String.valueOf(_hour-12);
       }
       else{
           type = "오전";
           hour = String.valueOf(_hour);
       }

       if(daychecklist.equals("")== false)
           daychecklist = _daychecklist;

       day = GetDateTime(mydate,"yyyy-MM-dd");
       min = GetDateTime(mydate,"MM");

   }


    public String GetDateTime(Date mydate , String commend){
            //권장  HH:mm:ss     or         HH:mm
            //권장 : yyyy-MM-dd    or     yyyy-MM
            SimpleDateFormat Format = new SimpleDateFormat(commend);
            String time = Format.format(mydate);
            return time;
    }


}
