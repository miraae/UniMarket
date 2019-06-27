package com.skkuseteam2.unimarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChattingRoomActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);
        listView = (ListView)findViewById(R.id.listView);

        class ChattingAdapter extends BaseAdapter{

            ArrayList<ChattingItem> items = new ArrayList<ChattingItem>();
            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Object getItem(int position) {
                return items.get(position);
            }

            public void addItem(ChattingItem item){
                items.add(item);
            }
            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ChattingItemView view = new ChattingItemView(getApplicationContext());
                ChattingItem item = items.get(position);
                view.setName(item.getName());
                view.setContent(item.getContent());
                return view;
            }
        }

        ChattingAdapter adapter=new ChattingAdapter();
        adapter.addItem(new ChattingItem("홍길동","안녕하세요"));
        listView.setAdapter(adapter);
        //listView.setBackgroundColor("#ffe262");
    }
}
