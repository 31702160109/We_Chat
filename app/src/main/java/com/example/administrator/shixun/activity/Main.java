package com.example.administrator.shixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.shixun.R;
import com.example.administrator.shixun.bean.MainBean;
import com.example.administrator.shixun.business.MainBse;
import com.example.administrator.shixun.dao.MainDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends AppCompatActivity {
    private EditText et_content;
    private ListView listView;
    private String key;
    private MainDao mainDao;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 100:
                    List<MainBean> list= (List<MainBean>) msg.obj;
                    if(list != null){
                        SimpleAdapter simpleAdapter=new SimpleAdapter(getApplicationContext(),
                                getData(list),
                                R.layout.wechat_list_item,
                                new String[]{"name","chat","time"},
                                new int[]{R.id.list_name,R.id.list_content,R.id.time});
                        listView.setAdapter(simpleAdapter);
                        et_content.setText("");
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化控件
        init();//初始化数据
        //发送点击事件
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();//发送消息并同步到界面
            }
        });
        findViewById(R.id.iv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passulist();
            }
        });

    }

    void initView(){
        et_content=findViewById(R.id.et_text);
        listView=findViewById(R.id.list);
        Intent intent=getIntent();
        key=intent.getStringExtra("key");
    }

    void init(){
        mainDao=new MainDao(getApplicationContext());
        new Thread(){
            @Override
            public void run() {
                List<MainBean> daoList= mainDao.find();
                Message message=new Message();
                message.what=100;
                message.obj=daoList;
                handler.sendMessage(message);
            }
        }.start();
    }

    void send(){
        new Thread(){
            @Override
            public void run() {
                String content=et_content.getText().toString().trim();
                MainBse mainBse=new MainBse();
                List<MainBean> list=mainBse.send(key,content);
                Message message=new Message();
                message.what=100;
                message.obj=list;
                handler.sendMessage(message);
                setdb(list);//当前数据存入数据库
            }
        }.start();
    }

    void setdb(List<MainBean> list){
        mainDao.delete();
        mainDao.insert(list);
    }

    private void passulist() {
        Intent intent=new Intent(getApplicationContext(),User_List.class);
        startActivityForResult(intent,1);
    }

    List<Map<String,String>> getData(List<MainBean> list){
        List<Map<String,String>> list_map=new ArrayList<Map<String,String>>();
        Map<String,String> map;
        for (int i = list.size()-1;i >= 0;i--){
            map=new HashMap<>();
            map.put("name",list.get(i).getName());
            map.put("chat",list.get(i).getChat());
            map.put("time",list.get(i).getTime());
            list_map.add(map);
        }
        return list_map;
    }
}
