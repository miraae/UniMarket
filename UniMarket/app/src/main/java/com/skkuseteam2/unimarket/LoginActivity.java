package com.skkuseteam2.unimarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference mDatabase_log;
    boolean logstate=false;
    EditText id, password;
    ArrayList<String> idlist = new ArrayList<String>();
    ArrayList<String> passwordlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        idlist.add("open66");
        idlist.add("open55");
        idlist.add("open44");
        passwordlist.add("openhack66");
        passwordlist.add("openhack55");
        passwordlist.add("openhack44");

        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.passsword);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "환영합니다!", Toast.LENGTH_SHORT).show();
               int flag = 0;
               int idx=-1;
                String idstr = id.getText().toString();
                for(int i =0;i<idlist.size();i++){
                    if(idlist.get(i).equals(idstr))
                    {
                        flag =1;
                        idx = i;
                        break;
                    }
                }
                if(flag == 1){
                    if(password.getText().toString().equals(passwordlist.get(idx)))
                        logstate=true;
                }
                if (logstate == true) {
                    Toast.makeText(getApplicationContext(),"환영합니다",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "아이디 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
        //================================================로그인 데이터 베이스

       /* mDatabase_log = FirebaseDatabase.getInstance().getReference().child("LoginBase");
        // mDatabase_log.setValue();
        UserInfo userInfo = new UserInfo(2, "openhack2019","open66" , "익산대학교","컴퓨터정보보안학과",2);
        SignIn("openhack2018","open66");

        mDatabase_log.push().setValue(userInfo);
        mDatabase_log.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                UserInfo user = dataSnapshot.getValue(UserInfo.class);
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

    //계정 추가
    public void SignUp(final UserInfo user){
        mDatabase_log = FirebaseDatabase.getInstance().getReference().child("LoginBase");
        mDatabase_log.setValue(user);
    }

    //계정 로그인"openhack2019","open66" 주면 로그인 ;
    public void SignIn(final String password, String nickname){

        mDatabase_log.orderByChild("nickname").equalTo(nickname).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(logstate == true)
                    return;
                UserInfo user = dataSnapshot.getValue(UserInfo.class);
                if(user.password.equals(password)== true)
                    logstate = true;
                else
                    logstate = false;

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

    }*/

