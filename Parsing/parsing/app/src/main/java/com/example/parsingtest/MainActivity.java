package com.example.parsingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class MainActivity extends AppCompatActivity {


    String address;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = "10.10.4.186";
        tv = (TextView)findViewById(R.id.text);

        Php_SendMessage("Openhack_SelectAllData.php");
        // task.execute("http://172.16.33.168:80/testconnection.php?id=테스트%20");
    }

    private void  Php_SendMessage(String _filename){
        PhpTest task = new PhpTest();
        task.execute("http://" + address + "/" + _filename);
        //task.execute("http://10.10.2.147:80/InsertBoard.php/?boardid=1&userid=1&boardsort=1&price=1000&icon=애완&maintext=제발도와주세요&location=동구&member=3/ ");
        //task.execute("http://서버 url/test.php?id=테스트 ㅇㅋ? / ");
    }

    String Connect(String... params){
        String output = "";
        try {
            //연결 url 설정
            URL url = new URL(params[0]);
            //커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //연결되었으면
            if(conn != null){
                conn.setConnectTimeout(10000);
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
            }else{
                System.out.println("실패");
                return "Fail";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Fail";
        }
        return output;
    }

    private class PhpTest extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... params) {
            return Connect(params);
        }
        String UserId, BoardId, Icon, Member, Main_text, Location;
        int Price;
         String Uploade_Date, Start_Time, End_Time;

        //반환 값은 여기로
        protected void onPostExecute(String str){

            tv.setText(str);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);

                UserId = jsonObject.getString("userid");
                BoardId= jsonObject.getString("boardid");
                Icon = jsonObject.getString("icon");
                Member = jsonObject.getString("member");
                Main_text = jsonObject.getString("maintext");
                Location = jsonObject.getString("location");
                Price = jsonObject.getInt("price");
                Uploade_Date = jsonObject.getString("uploadtime");
                Start_Time = jsonObject.getString("starttime");
                End_Time = jsonObject.getString("endtime");

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

                    String tvstring = tv.getText().toString();
                    tv.setText(tvstring + "\n유저아이디 : " + UserId + "\n보드아이디 : " + BoardId + "\n아이콘 : " + Icon +
                               "\n주내용 : " + Main_text + "\n장소 : " + Location + "\n가격 : " + Price + "\n게시날짜 : " + Uploade_Date +
                                "\n일 시작일 : " + Start_Time + "\n일 종료일 : " + End_Time);


        }

    }

}