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
    ImageView icon2;
    TextView address;
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
        icon2 = (ImageView)findViewById(R.id.icon2);
        address = (TextView)findViewById(R.id.address);
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

    public void setIcon2(int name)
    {
        icon2.setImageResource(name);
    }
    public void setAdress(String name){
        address.setText(name);
    }

    public void setImg(int ima1,int ima2,int ima3,int ima4){
        if(ima1!=0) {
            img1.setImageResource(ima1);
        }
        if(ima2!=0) {
            img2.setImageResource(ima2);
        }
        if(ima3!=0) {
            img3.setImageResource(ima3);
        }
        if(ima4!=0) {
            img4.setImageResource(ima4);
        }
    }


   public void setMoney(String money1){
        money.setText(money1);
    }

}
