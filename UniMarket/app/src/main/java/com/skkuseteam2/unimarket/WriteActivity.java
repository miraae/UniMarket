package com.skkuseteam2.unimarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {
    ImageButton backButton;
    ImageButton move,pack,car,animal,etc;
    ImageButton plus,minus;
    ImageButton publishButton;
    ImageView img1,img2,img3,img4;
    int size=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        backButton = (ImageButton)findViewById(R.id.backButton);
        move = (ImageButton)findViewById(R.id.move);
        pack = (ImageButton)findViewById(R.id.pack);
        car = (ImageButton)findViewById(R.id.car);
        animal = (ImageButton)findViewById(R.id.animal);
        etc = (ImageButton)findViewById(R.id.etc);
        plus = (ImageButton)findViewById(R.id.plus);
        minus = (ImageButton)findViewById(R.id.minus);

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img4 = (ImageView)findViewById(R.id.img4);

        publishButton = (ImageButton)findViewById(R.id.publishButton);

        /*publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarketItem item = new MarketItem();
                Intent intent = new Intent(WriteActivity.this,MainActivity.class);
            }
        });*/

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size>=4)
                    return;
                else{
                    size++;
                    if(size==1) {
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.INVISIBLE);
                        img3.setVisibility(View.INVISIBLE);
                        img4.setVisibility(View.INVISIBLE);
                    }
                    else if(size == 2){
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.VISIBLE);
                        img3.setVisibility(View.INVISIBLE);
                        img4.setVisibility(View.INVISIBLE);
                    }
                    else if(size == 3){
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.VISIBLE);
                        img3.setVisibility(View.VISIBLE);
                        img4.setVisibility(View.INVISIBLE);
                    }
                    else if(size == 4){
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.VISIBLE);
                        img3.setVisibility(View.VISIBLE);
                        img4.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size<=0)
                    return;
                else {
                    size--;
                    if(size==1) {
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.INVISIBLE);
                        img3.setVisibility(View.INVISIBLE);
                        img4.setVisibility(View.INVISIBLE);
                    }
                    else if(size == 2){
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.VISIBLE);
                        img3.setVisibility(View.INVISIBLE);
                        img4.setVisibility(View.INVISIBLE);
                    }
                    else if(size == 3){
                        img1.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.VISIBLE);
                        img3.setVisibility(View.VISIBLE);
                        img4.setVisibility(View.INVISIBLE);
                    }
                    else if(size == 0){
                        img1.setVisibility(View.INVISIBLE);
                        img2.setVisibility(View.INVISIBLE);
                        img3.setVisibility(View.INVISIBLE);
                        img4.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.move:
                move.setImageResource(R.drawable.cat_move_on);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc);
                break;
            case R.id.pack:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack_on);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc);
                break;
            case R.id.car:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car_on);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc);
                break;
            case R.id.animal:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet_on);
                etc.setImageResource(R.drawable.cat_etc);
                break;
            case R.id.etc:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc_on);
        }
    }
}
