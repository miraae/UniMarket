package com.skkuseteam2.unimarket;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardControll {

        public int userid;  //유저 아이디
        public int boardid; //게시판 번호

        public int price;   //금액---------
        public int member;  //맴버 수------
        public int boardsort; //구인 구직-----
        public int icon;  //카테고리 ------

        public String maintext; //메인택스트

        public String location; //지역  -------

        public TimeModel starttime;
        public TimeModel endtime;

        public String daystring; // 데이 스트링

        public BoardControll(){

        }
        public BoardControll(int _userid, int _boardid,int _price,int _member,int _boardsort,int _icon,String _maintext,String _location,TimeModel _endtime,TimeModel _starttime,String _daystring){
                userid =_userid;
                boardid = _boardid;
                price = _price;
                member = _member;
                boardsort = _boardsort;
                icon = _icon;
                maintext = _maintext;
                location = _location;
                daystring= _daystring;
                starttime = _starttime;
                endtime  =  _endtime;
        }

        public String getAddress() {
                StringBuilder str = new StringBuilder("10.10.4.186/OpenHack_InsertEmployBoard.php/?boardid=");
                str.append(boardid);
                str.append("&userid=");
                str.append(userid);
                str.append("&boardsort=");
                str.append(boardsort);
                str.append("&price=");
                str.append(price);
                str.append("&icon=");
                str.append(icon);
                str.append("&maintext=");
                str.append(maintext);
                str.append("&location=");
                str.append(location);
                str.append("&endtime=");
                str.append(endtime.MakeDateString());
                str.append("&member=");
                str.append(member);
                str.append("&starttime=");
                str.append(starttime.MakeDateString());
                str.append("&daystring=");
                str.append(daystring);

            return  str.toString();

        }



}
