package com.skkuseteam2.unimarket;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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


}
