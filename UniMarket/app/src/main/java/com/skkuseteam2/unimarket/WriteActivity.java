package com.skkuseteam2.unimarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WriteActivity extends AppCompatActivity {
    ImageButton backButton;
    ImageButton move,pack,car,animal,etc;

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
