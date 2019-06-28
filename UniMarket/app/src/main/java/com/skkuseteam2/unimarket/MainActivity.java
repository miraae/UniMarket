package com.skkuseteam2.unimarket;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {

    MarketAdapter adapter = new MarketAdapter();

    class MarketAdapter extends BaseAdapter {
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

        public void addItem(MarketItem item) {
            items.add(item);
        }

        @Override
            public View getView(int position, View convertView, ViewGroup parent) {
            MarketItemView view = null;
            if (convertView == null) {
                view = new MarketItemView(getApplicationContext());
            } else {
                view = (MarketItemView) convertView;
            }
            MarketItem item = items.get(position);
            view.setIcon(item.getIcon());
            view.setIcon2(item.getIcon2());
            view.setAddress(item.getAddress());
            view.setImg(item.getImg1(), item.getImg2(), item.getImg3(), item.getImg4());
            view.setMoney(item.getMoney());
            return view;
        }
    }
    static boolean logined = false;

    ImageButton searchButton;
    static ArrayList<MarketItem> items = new ArrayList<MarketItem>();

    Intent data = null;

    private DatabaseReference mDatabase;

    private ArrayList<BoardControll> boardlist = new ArrayList<>();

    public void initdatabase() {

        //==============================================Main Board =======================
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MainBoard");
        BoardControll boardControll = new BoardControll(2, 2, 9000, 3, 1, 4, "많이 많이 지원해주세요",
                "익산 익스프레스", "2019-06-28 15:30:45", "2019-06-29 06:10:35", "금#토#일");
        // mDatabase.push().setValue(boardControll);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BoardControll item = dataSnapshot.getValue(BoardControll.class);
                boardlist.add(item);
                if(item.price !=1) {
                    adapter.addItem(new MarketItem(item.icon, R.drawable.find, item.location, R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, item.price + ""));
                    adapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //DB가 변경 되었을 때
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initdatabase();
        data = getIntent();
        try {
            if (data != null) {
                String location = data.getStringExtra("locating");
                int member = data.getIntExtra("member", 1);
                int icon = data.getIntExtra("icon", 1);
                int price = data.getIntExtra("price", 1);
                String maintext = data.getStringExtra("maintext");
                String start = data.getStringExtra("start");
                String end = data.getStringExtra("end");


                BoardControll item = new BoardControll(101, boardlist.size() - 1, price, member, 0, icon, maintext, location, end, start, null);
                UploadBoard(item);
              //  adapter.addItem
               // System.out.println(location + " " + member + " " + icon + " " + maintext + " " + start[0] + " " + end[0]);
            }
        }
        catch(Exception e)
        {


        }
        if (!logined) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            logined = true;
        }

            ImageButton menuButton = (ImageButton) findViewById(R.id.menuButton);
            searchButton = (ImageButton) findViewById(R.id.searchButton);

            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                }
            });

            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                }
            });

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setBackgroundColor(Color.WHITE);


            /*adapter.addItem(new MarketItem(R.drawable.move, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "8350"));
            adapter.addItem(new MarketItem(R.drawable.pack_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "9000"));
            adapter.addItem(new MarketItem(R.drawable.pet, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "9500"));
            adapter.addItem(new MarketItem(R.drawable.car, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "8500"));
            adapter.addItem(new MarketItem(R.drawable.pet_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "10000"));
            adapter.addItem(new MarketItem(R.drawable.car_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "8350"));
            adapter.addItem(new MarketItem(R.drawable.pet_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "9500"));
            adapter.addItem(new MarketItem(R.drawable.pack, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "8700"));
            adapter.addItem(new MarketItem(R.drawable.pet, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "9500"));
            adapter.addItem(new MarketItem(R.drawable.pet_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "9500"));
            adapter.addItem(new MarketItem(R.drawable.etc, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "8350"));
            adapter.addItem(new MarketItem(R.drawable.move_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "8350"));
            adapter.addItem(new MarketItem(R.drawable.etc_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, R.drawable.hum_icon, 0, "8800"));
            adapter.addItem(new MarketItem(R.drawable.pet, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "9500"));
            adapter.addItem(new MarketItem(R.drawable.pet_y, R.drawable.find, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "9500"));
            adapter.addItem(new MarketItem(R.drawable.pet, R.drawable.guhae, "대구광역시 달서구 진천동", R.drawable.hum_icon, R.drawable.hum_icon, 0, 0, "9500"));*/
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    MarketItem item = items.get(i);
                    intent.putExtra("price", item.getMoney());
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                }
            });
        }


    public void UploadBoard(BoardControll board) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MainBoard");
        mDatabase.push().setValue(board);
    }

    public String MakeDateString(String _am, String _year, String _hour, String _min) {
        int hour = Integer.parseInt(_hour);
        if (_am.equals("오후") == true)
            hour += 12;
        return _year + " " + hour + ":" + _min + ":00";
    }

    public Date MakeDate(String time) {

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date to;
        try {
            to = transFormat.parse(time);
            return to;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}




