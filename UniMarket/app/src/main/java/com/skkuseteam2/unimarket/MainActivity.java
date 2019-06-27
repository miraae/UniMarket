package com.skkuseteam2.unimarket;

<<<<<<< HEAD
=======
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 6c734431afe0573e967788a3a6a03928c4462b9b
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 삭제할 코드 (Detail Activity 먼저 실행)
        Intent intent = new Intent(getApplicationContext(), ChattingRoomActivity.class);
        startActivity(intent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                view.setIcon(R.drawable.test);
                view.setTextView1(item.getStr());
                view.setImg(R.drawable.test,R.drawable.test,R.drawable.test,R.drawable.test);
                view.setMoney(item.getMoney());
                return view;
            }
        }

        ListView listView = (ListView)findViewById(R.id.listView);
        MarketAdapter adapter = new MarketAdapter();
        adapter.addItem(new MarketItem(null,"구인",null,null,null,null,"10000원"));
        listView.setAdapter(adapter);
    }
}