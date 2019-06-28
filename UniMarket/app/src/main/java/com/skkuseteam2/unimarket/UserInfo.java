package com.skkuseteam2.unimarket;

public class UserInfo {

    public  int userid;
    public  String password;
    public  String nickname;
    public  String school;
    public  String major;
    public  int picture;

    public UserInfo(){

    }

    public UserInfo(int userid,String password,String nickname,String school,String major,int picture) {
        this.userid = userid; this.password = password; this.nickname = nickname; this.school = school; this.major = major; this.picture = picture;
    }


}
