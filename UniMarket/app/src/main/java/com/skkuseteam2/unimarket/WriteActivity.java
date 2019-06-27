package com.skkuseteam2.unimarket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {
    ImageButton backButton;
    ImageButton move,pack,car,animal,etc;
    ImageButton plus,minus;
    ImageButton publishButton;
    ImageView img1,img2,img3,img4;
    int startyear,startmonth,startday;
    int endyear,endmonth,endday;

    EditText price,content;
    TextView t1,t2;
    ImageButton startDateButton,endDateButton;
    int size=4;
    int icon;
    EditText starthour,startminute,endhour,endminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        startDateButton = (ImageButton)findViewById(R.id.startDateButton);
        endDateButton = (ImageButton)findViewById(R.id.endDateButton);

        starthour = (EditText)findViewById(R.id.starthour);
        startminute = (EditText)findViewById(R.id.startminute);
        endhour = (EditText)findViewById(R.id.endhour);
        endminute = (EditText)findViewById(R.id.endminute);

        price = (EditText)findViewById(R.id.price);
        content = (EditText)findViewById(R.id.content);
        backButton = (ImageButton)findViewById(R.id.backButton);
        move = (ImageButton)findViewById(R.id.move);
        pack = (ImageButton)findViewById(R.id.pack);
        car = (ImageButton)findViewById(R.id.car);
        animal = (ImageButton)findViewById(R.id.animal);
        etc = (ImageButton)findViewById(R.id.etc);
        plus = (ImageButton)findViewById(R.id.plus);
        minus = (ImageButton)findViewById(R.id.minus);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img4 = (ImageView)findViewById(R.id.img4);

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_GetDateTime();
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_GetDateTime2();
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t1.getText().toString().equals("PM"))
                    t1.setText("AM");
                else
                    t1.setText("PM");
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t2.getText().toString().equals("AM"))
                    t2.setText("PM");
                else
                    t2.setText("AM");
            }
        });

        publishButton = (ImageButton)findViewById(R.id.publishButton);





        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this,MainActivity.class);
                intent.putExtra("locating","대구 수성구");
                intent.putExtra("member",size);
                intent.putExtra("price",price.getText().toString());
                intent.putExtra("icon",icon);
                intent.putExtra("maintext",content.getText().toString());
                String start =t1.getText().toString()+"@"+startyear+"-"+startmonth+"-"+startday+"@"+starthour.getText().toString()+"@"+startminute.getText().toString();
                String end = t2.getText().toString()+"@"+endyear+"-"+endmonth+"-"+endday+"@"+endhour.getText().toString()+"@"+endminute.getText().toString();
                intent.putExtra("start",start);
                intent.putExtra("end",end);
                startActivity(intent);
            }
        });

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
    public void Click_GetDateTime() {
        DatePickerDialog dialog = new DatePickerDialog(this, listener, 2019, 06, 28);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
            startyear=year; startmonth = monthOfYear; startday = dayOfMonth;

        }

    };

    public void Click_GetDateTime2() {
        DatePickerDialog dialog = new DatePickerDialog(this, listener2, 2019, 06, 28);
        dialog.show();
    }
    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
            endyear=year; endmonth = monthOfYear; endday = dayOfMonth;
        }

    };

    public void onClick(View view){
        switch(view.getId()){
            case R.id.move:
                move.setImageResource(R.drawable.cat_move_on);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc);
                icon = R.drawable.move;
                break;
            case R.id.pack:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack_on);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc);
                icon = R.drawable.pack;
                break;
            case R.id.car:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car_on);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc);
                icon = R.drawable.car;
                break;
            case R.id.animal:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet_on);
                etc.setImageResource(R.drawable.cat_etc);
                icon= R.drawable.pet;
                break;
            case R.id.etc:
                move.setImageResource(R.drawable.cat_move);
                pack.setImageResource(R.drawable.cat_pack);
                car.setImageResource(R.drawable.cat_car);
                animal.setImageResource(R.drawable.cat_pet);
                etc.setImageResource(R.drawable.cat_etc_on);
                icon = R.drawable.etc;
        }
    }
}
