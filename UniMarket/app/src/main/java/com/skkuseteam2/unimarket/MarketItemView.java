package com.skkuseteam2.unimarket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MarketItemView extends LinearLayout {

    ImageView icon;
    TextView textView1;
    ImageView img1,img2,img3,img4;
    TextView money;

    public MarketItemView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MarketItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.market_item,this,true);
        icon = (ImageView)findViewById(R.id.icon);
        textView1 = (TextView)findViewById(R.id.textView1);
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img4 = (ImageView)findViewById(R.id.img4);
        money = (TextView)findViewById(R.id.money);
    }

    public void setIcon(int name)
    {
        icon.setImageResource(name);
    }

    public void setTextView1(String name){
        textView1.setText(name);
    }

    public void setImg(int ima1,int ima2,int ima3,int ima4){
       img1.setImageResource(ima1);
       img2.setImageResource(ima2);
       img3.setImageResource(ima3);
       img4.setImageResource(ima4);
    }

   public void setMoney(String money1){
        String str = money.getText().toString();
        str = str+money1;
        money.setText(str);
    }
}
