package com.skkuseteam2.unimarket;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton menuButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuButton = (ImageButton)findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyPageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
                finish();
            }
        });

        class MarketAdapter extends BaseAdapter{
            ArrayList<MarketItem> items = new ArrayList<MarketItem>();
            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Object getItem(int position) {
                return items.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            public void addItem(MarketItem item){
                items.add(item);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MarketItemView view = new MarketItemView(getApplicationContext());
                MarketItem item = items.get(position);
                view.setIcon(item.getIcon());
                view.setIcon2(item.getIcon2());
                view.setAddress(item.getAddress());
                view.setImg(item.getImg1(),item.getImg2(),item.getImg3(),item.getImg4());
                view.setMoney(item.getMoney());
                return view;
            }
        }

        ListView listView = (ListView)findViewById(R.id.listView);
        MarketAdapter adapter = new MarketAdapter();
        adapter.addItem(new MarketItem(R.drawable.detail_move,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        listView.setAdapter(adapter);
    }
}