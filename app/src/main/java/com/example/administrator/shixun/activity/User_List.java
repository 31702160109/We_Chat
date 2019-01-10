package com.example.administrator.shixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.shixun.R;
import com.example.administrator.shixun.bean.UserBean;
import com.example.administrator.shixun.business.UserBse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User_List extends AppCompatActivity {
    private ListView listView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            List<UserBean> list= (List<UserBean>) msg.obj;
            switch (msg.what) {
                case 100:
                    SimpleAdapter simpleAdapter=new SimpleAdapter(getApplicationContext(),
                            getData(list),
                            R.layout.user_list_item,
                            new String[]{"name"},
                            new int[]{R.id.list_name});
                    listView.setAdapter(simpleAdapter);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        init();
        getList();
        findViewById(R.id.iv_ret).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passmain();
            }
        });
    }
    void init(){
        listView=findViewById(R.id.list);
    }
    void getList(){
        new Thread(){
            @Override
            public void run() {
                UserBse userBse=new UserBse();
                List<UserBean> list=userBse.getList();
                Message message=new Message();
                message.what=100;
                message.obj=list;
                handler.sendMessage(message);
            }
        }.start();
    }
    public void passmain(){
        Intent intent=new Intent(this,Main.class);
        setResult(1,intent);
        finish();
    }
    List<Map<String,String>> getData(List<UserBean> list){
        List<Map<String,String>> list_map=new ArrayList<Map<String,String>>();
        Map<String,String> map;
        for (int i=list.size()-1;i>=0;i--){
            map=new HashMap<>();
            map.put("name",list.get(i).getName());
            map.put("user",list.get(i).getUser());
            list_map.add(map);
        }
        return list_map;
    }
}
