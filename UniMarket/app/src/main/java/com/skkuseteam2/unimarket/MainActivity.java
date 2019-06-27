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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageButton searchButton;
    static ArrayList<MarketItem> items = new ArrayList<MarketItem>();
    private ArrayList<BoardControll> boardlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        ImageButton menuButton=(ImageButton)findViewById(R.id.menuButton);
        searchButton = (ImageButton)findViewById(R.id.searchButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,MyPageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
            }
        });

        class MarketAdapter extends BaseAdapter{
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
                MarketItemView view = null;
                if(convertView == null){
                    view =  new MarketItemView(getApplicationContext());
                }
                else{
                    view = (MarketItemView) convertView;
                }
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
        listView.setBackgroundColor(Color.WHITE);
        MarketAdapter adapter = new MarketAdapter();
        adapter.addItem(new MarketItem(R.drawable.move,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.pack_y,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.car,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,0,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.pet_y,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.car_y,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.pack,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.pet,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,0,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.etc,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.move_y,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        adapter.addItem(new MarketItem(R.drawable.etc_y,R.drawable.find,"대구광역시 달서구 진천동",R.drawable.hum_icon,R.drawable.hum_icon,R.drawable.hum_icon,0,"4000"));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
            }
        });

        //OpenHack_SelectAllBookMarket.php 호출해서 파싱.
        //if(Data.otherid == myid || Data.userid == myid){
        //
        //



/*
        Date date = MakeDate("2019-12-12 01:42:22");
        TimeModel timeModel = new TimeModel(date);
        BoardControll boardControll = new BoardControll(1,1,30000,4,2,
                "이사","도와주세요","서구",timeModel ,timeModel,"월#목#금");

        System.out.println(boardControll.getAddress());
        String temp = boardControll.getAddress();

        //========-
        // Php_SendMessage("http://"+temp);
        Php_SendMessage("http://10.10.4.186/Openhack_SelectAllData.php");
        //==============
*/
    }




    //받아온 날짜 문자 파싱
    public void getDate(String selectday){
        String str[] =   selectday.split("#");
        for(int i =0; i<str.length;i++){
            //여기서 날짜 설정해주시면 됩니다.
            switch (str[i]) {
                case "일":  break;
                case "월":  break;
                case "화":  break;
                case "수":  break;
                case "목":  break;
                case "금":  break;
                case "토":  break;
            }
        }
    }




    //day_commend = "2019-04-05" 를 주면 그것에 맞는 요일을 반환
    public  String getDateDay(String day_commend) {
        try{

            String day = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date nDate = dateFormat.parse(day_commend);
            Calendar cal = Calendar.getInstance();
            cal.setTime(nDate);

            int dayNum = cal.get(Calendar.DAY_OF_WEEK);
            switch (dayNum) {
                case 1: day = "일"; break;
                case 2: day = "월"; break;
                case 3: day = "화"; break;
                case 4: day = "수"; break;
                case 5: day = "목"; break;
                case 6: day = "금"; break;
                case 7: day = "토"; break;
            }
            return day;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //String commend = 현재 시간.
    public String CheckTime(String commend){
        try {

            // "2013-04-08 10:10:10"
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date to = transFormat.parse(commend);
            Calendar cal = Calendar.getInstance();
            cal.setTime(to);

            int check = cal.get(cal.AM_PM);
            if(check == cal.AM)
                return "오전";
            else
                return "오후";


        } catch (ParseException e) {
            e.printStackTrace();
            return  "";
        }
    }

    public String MakeDateString(String _am , String _year , String _hour,String _min){
        int hour = Integer.parseInt(_hour);
        if(_am.equals("오후")==true)
            hour += 12;
        return _year+" "+ hour +":"+_min +":00";
    }

    public Date MakeDate(String time){

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




    private void  Php_SendMessage(String str){
        PhpTest task = new PhpTest();
        //task.execute("http://" + address + "/" + _filename);

        task.execute(str);
        //task.execute("http://10.10.2.147:80/InsertBoard.php/?boardid=1&userid=1&boardsort=1&price=1000&icon=애완&maintext=제발도와주세요&location=동구&member=3/ ");
        //task.execute("http://서버 url/test.php?id=테스트 ㅇㅋ? / ");
    }



    private class PhpTest extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {
            String output = "";
            try {
                //연결 url 설정
                System.out.println(params[0]);
                URL url = new URL(params[0]);

                //커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDefaultUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                // conn.setConnectTimeout(100000);
                //  InputStreamReader in = new InputStreamReader((InputStream)conn.getContent(), "utf-8");

                //연결되었으면
                if(conn != null){
                    conn.setConnectTimeout(100000);
                    conn.setUseCaches(false);

                    //연결된 코드가 리턴되면
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        int i = 0 ;
                        for(;;){
                            //웹상에 보이는 텍스트를 라인단위로 읽어 저장
                            String line = br.readLine();
                            if(line == null) { //라인이 없을 때
                                break;
                            }
                            i++;
                            output += line;
                        }
                        br.close();

                    }
                    conn.disconnect();
                    return output;
                }else{
                    System.out.println("실패");
                    return "여기";
                }
            }catch (Exception e){
                e.printStackTrace();
                return "하..";
            }

        }

        //반환 값은 여기로
        protected void onPostExecute(String str){
            String OutputData = "";
            JSONObject jsonResponse;
            Toast.makeText(getApplication(),str,Toast.LENGTH_LONG).show();

            try {

                /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                jsonResponse = new JSONObject(str);

                /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                /*******  Returns null otherwise.  *******/
                JSONArray jsonMainNode = jsonResponse.optJSONArray("SelectAll");

                /*********** Process each JSON Node ************/

                int lengthJsonArr = jsonMainNode.length();

                for(int i=0; i < lengthJsonArr; i++)
                {
                    /****** Get Object for each JSON node.***********/
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                    Date StartTime = MakeDate(jsonChildNode.getString("starttime"));
                    Date EndTime = MakeDate(jsonChildNode.getString("endtime"));


                    TimeModel St = new TimeModel(StartTime);
                    TimeModel Et = new TimeModel(EndTime);


                    BoardControll boardControll = new BoardControll((jsonChildNode.getInt("userid")), (jsonChildNode.getInt("boardid")), (jsonChildNode.getInt("price")),
                            (jsonChildNode.getInt("member")), (jsonChildNode.getInt("noardsort")), (jsonChildNode.getString("icon")), (jsonChildNode.getString("maintext")),
                            (jsonChildNode.getString("location")), St, Et, (jsonChildNode.getString("daystring")));

                    boardlist.add(boardControll);
                }


            } catch (JSONException e) {

                e.printStackTrace();
            }

        }

    }

}