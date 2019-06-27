package com.skkuseteam2.unimarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MyPageActivity extends AppCompatActivity {
    ImageButton cancel;
    Button people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        cancel = (ImageButton)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
                finish();
            }
        });

        people = (Button)findViewById(R.id.people);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this,WriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
