package com.skkuseteam2.unimarket;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("message");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //DB가 변경되면 계속해서 호출되는 부분.
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                 //DB가 변경 되었을 때
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

       Toast.makeText(getApplication(),MakeDateString("오후","2019-12-12","7","33") ,Toast.LENGTH_LONG).show();
    }

    //채팅방 삭제(양방향 삭제) 보드 id값
    public void DeleteChatBoard(int boardid){


        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("message");
        db.orderByChild("boardid").equalTo(boardid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                //단톡방 자체를 나간경우.
                dataSnapshot.getRef().setValue(null);
            }
            //region 오버라이딩 부분
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
            //endregion
        });
    }

    //채팅 내용 삭제(양방향 삭제) List에 들어간 ChatModel의 timelog 값
    public void DeleteChatMessage(String time){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("message");
        db.orderByChild("timelog").equalTo(time).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                //단톡방 자체를 나간경우.
                ChatModel user = dataSnapshot.getValue(ChatModel.class);
               // if(user.otherid == id) 나중에 자신의 아이디를 가지고 있을시 if 문을 토대로 자신의 글을 지울 수 있음.
                dataSnapshot.getRef().setValue(null);
            }
            //region 오버라이딩 부분
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
            //endregion
        });
    }

    //방에서 쫓아내기 자기 자신 아이디를 주면 방 나가기
    public void DeleteMember(int otherid){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("message");
        db.orderByChild("otherid").equalTo(otherid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                dataSnapshot.getRef().setValue(null);
            }
            //region 오버라이딩 부분
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
            //endregion
        });
    }

    //메세지 샌딩 부분 ChatModel 생성자 부분 참고
    public void SendMessage(ChatModel packet){
        ChatModel user = new ChatModel(1,"홍길동", "메세지 갔습당", 01, 1);
        mDatabase.push().setValue(user);
    }



    //받아온 날짜 문자 파싱
    public void getDate(String selectday){
        String str[] =   selectday.split("#");
        for(int i =0; i<str.length;i++){
            //여기서 날짜 설정해주시면 됩니다.
            switch (str[i]) {
                case "일":  break;
                case "월":  break;
                case "화":  break;
                case "수":  break;
                case "목":  break;
                case "금":  break;
                case "토":  break;
            }
        }
    }

    //day_commend = "2019-04-05" 를 주면 그것에 맞는 요일을 반환
    public  String getDateDay(String day_commend) {
        try{

        String day = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date nDate = dateFormat.parse(day_commend);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);

        int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        switch (dayNum) {
            case 1: day = "일"; break;
            case 2: day = "월"; break;
            case 3: day = "화"; break;
            case 4: day = "수"; break;
            case 5: day = "목"; break;
            case 6: day = "금"; break;
            case 7: day = "토"; break;
        }
        return day;
        } catch (Exception e) {
           e.printStackTrace();
           return "";
        }
    }

    //String commend = 현재 시간.
    public String CheckTime(String commend){
        try {

            // "2013-04-08 10:10:10"
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date to = transFormat.parse(commend);
            Calendar cal = Calendar.getInstance();
            cal.setTime(to);

            int check = cal.get(cal.AM_PM);
            if(check == cal.AM)
                return "오전";
            else
                return "오후";


        } catch (ParseException e) {
            e.printStackTrace();
            return  "";
        }
    }

    public String MakeDateString(String _am , String _year , String _hour,String _min){
        int hour = Integer.parseInt(_hour);
        if(_am.equals("오후")==true)
            hour += 12;
        return _year+" "+ hour +":"+_min +":00";
    }


    public Date MakeDate(String time){

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date to;
        try {
             to = transFormat.parse(time);
            return to;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }




}
