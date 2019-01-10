package com.example.administrator.shixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.shixun.R;
import com.example.administrator.shixun.business.RegisterBes;

public class Register extends AppCompatActivity {
    private Button btn;
    private ImageView cloes;
    private EditText et_name;
    private EditText et_username;
    private EditText et_password;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    Toast.makeText(getApplicationContext(), msg.obj.toString().trim(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passfzhuce();
            }
        });
        findViewById(R.id.cloes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passcloes();
            }
        });
        init();
    }
    void init(){
        et_name=findViewById(R.id.et_name);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
    }
    public void passfzhuce(){
        new Thread(){
            @Override
            public void run() {
                String name=Register.this.et_name.getText().toString().trim();
                String username=Register.this.et_username.getText().toString().trim();
                String password=Register.this.et_password.getText().toString().trim();
                RegisterBes registerBes=new RegisterBes();
                if (registerBes.doRegister(name,username,password)){
                    Message msg=new Message();
                    msg.what=100;
                    msg.obj="注册成功";
                    handler.sendMessage(msg);
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();
                }else {
                    Message msg=new Message();
                    msg.what=100;
                    msg.obj="注册失败";
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }
    public void passcloes(){
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
    }
}
