package com.example.phpinsert;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class insert extends AppCompatActivity {

    TextView tvi;
    String address = new String();
    private String BoardId, UserId, BoardSort, Price, Icon, Main_Text, Location, End_Time, Member, Start_Time, DayString;

    public String getAddress() {
        address = "https://10.10.4.186/OpenHack_InsertEmployBoard.php/?boardid="+BoardId+"&userid="+UserId+"&boardsort="+BoardSort+"&price="+Price+"&icon="+Icon+"&maintext="+Main_Text+"&location="+Location+"&endtime="+End_Time+"&member="+Member+"&starttime="+Start_Time+"&daystring="DayString);
        return address;
    }

    URL url;

    {
        try {
            url = new URL(address);
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
