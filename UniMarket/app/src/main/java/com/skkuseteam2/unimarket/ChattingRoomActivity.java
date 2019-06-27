package com.skkuseteam2.unimarket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChattingRoomActivity extends AppCompatActivity {

    private int uid = 1;
    private int bid = 1;

    ListView listView;

    private DatabaseReference mDatabase;

    ArrayList<ChatModel> chatModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);
        listView = (ListView)findViewById(R.id.listView);

        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        loadChatting();

        ChattingAdapter adapter=new ChattingAdapter(chatModels);
        //adapter.addItem(new ChattingItem("홍길동","안녕하세요"));
        listView.setAdapter(adapter);
        //listView.setBackgroundColor("#ffe262");
    }

    public void SendTextMessage(View view) {
        //edittext
        EditText editText = findViewById(R.id.chatEditText);
        String chatString = editText.getText().toString();
        editText.setText("");

        ChatModel chat = new ChatModel(1,"김길동", chatString,1,1);
        SendMessage(chat);
    }

    class ChattingAdapter extends BaseAdapter{

        ArrayList<ChatModel> items = new ArrayList<ChatModel>();

        public ChattingAdapter(ArrayList<ChatModel> chatModels){
            items = chatModels;
        }

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatModelView view = new ChatModelView(getApplicationContext());
            ChatModel item = items.get(position);
            view.setName(item.getName());
            view.setContent(item.getText());

            if (item.getOtherid() == uid)
                changeTheme(view);

            return view;
        }

        public void addItem(ChatModel item){
            items.add(item);
        }

        protected void changeTheme(ChatModelView view) {

            LinearLayout linearLayout = findViewById(R.id.layoutForAlign);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            linearLayout.setLayoutParams(params);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_START, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
            params.addRule(RelativeLayout.ALIGN_PARENT_END, 1);
            ImageView profileImg = (ImageView)findViewById(R.id.proImage);
            profileImg.setVisibility(View.GONE);

            TextView timeTextview = (TextView)findViewById(R.id.timeText);
            timeTextview.setVisibility(View.GONE);

            TextView nameText = (TextView)findViewById(R.id.name);
            nameText.setVisibility(View.GONE);

            TextView contentText = (TextView)findViewById(R.id.content);
            contentText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.chatting_background_white));
            contentText.setTextColor(Color.parseColor("#525252"));
        }

        /*
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChattingItemView view = new ChattingItemView(getApplicationContext());
            ChattingItem item = items.get(position);
            view.setName(item.getName());
            view.setContent(item.getContent());
            return view;
        }*/
    }

    protected void loadChatting() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("message");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //DB가 변경되면 계속해서 호출되는 부분.


                ChatModel user = dataSnapshot.getValue(ChatModel.class);
                /*
                chatModels.add(user);
                ChattingAdapter adapter = (ChattingAdapter)listView.getAdapter();
                adapter.notifyDataSetChanged();*/

                if (user.getBoardid() == bid) {
                    chatModels.add(user);
                    ChattingAdapter adapter = (ChattingAdapter)listView.getAdapter();
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

    //채팅방 삭제(양방향 삭제) 보드 id값
    public void DeleteChatBoard(int boardid){


        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("message");
        db.orderByChild("boardid").equalTo(boardid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                //단톡방 자체를 나간경우.
                dataSnapshot.getRef().setValue(null);
            }
            //region 오버라이딩 부분
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
            //endregion
        });
    }

    //채팅 내용 삭제(양방향 삭제) List에 들어간 ChatModel의 timelog 값
    public void DeleteChatMessage(String time){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("message");
        db.orderByChild("timelog").equalTo(time).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                //단톡방 자체를 나간경우.
                ChatModel user = dataSnapshot.getValue(ChatModel.class);
                // if(user.otherid == id) 나중에 자신의 아이디를 가지고 있을시 if 문을 토대로 자신의 글을 지울 수 있음.
                dataSnapshot.getRef().setValue(null);
            }
            //region 오버라이딩 부분
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
            //endregion
        });
    }

    //방에서 쫓아내기 자기 자신 아이디를 주면 방 나가기
    public void DeleteMember(int otherid){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("message");
        db.orderByChild("otherid").equalTo(otherid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                dataSnapshot.getRef().setValue(null);
            }
            //region 오버라이딩 부분
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
            //endregion
        });
    }

    //메세지 샌딩 부분 ChatModel 생성자 부분 참고
    public void SendMessage(ChatModel packet){

        mDatabase.push().setValue(packet);
    }
}
