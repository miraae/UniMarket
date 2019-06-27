package com.skkuseteam2.unimarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 삭제할 코드 (Detail Activity 먼저 실행)
        Intent intent = new Intent(getApplicationContext(), ChattingRoomActivity.class);
        startActivity(intent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}