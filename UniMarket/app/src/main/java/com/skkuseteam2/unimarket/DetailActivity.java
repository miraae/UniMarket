package com.skkuseteam2.unimarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int itemId = 1;
    TextView priceText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        priceText = (TextView)findViewById(R.id.priceText);
        Intent intent = getIntent();
        if(intent!=null){
            String money = intent.getStringExtra("price");
            priceText.setText(money);
        }
        ImageButton backBtn = (ImageButton)findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
            }
        });

        ImageButton chatBtn = (ImageButton)findViewById(R.id.chatButton);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ChattingRoomActivity.class);
                intent.putExtra("boardid", itemId);
                startActivity(intent);
            }
        });
    }
}
