package com.example.administrator.shixun.business;

import com.example.administrator.shixun.http.RegisterHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterBes {
    public boolean doRegister(String name,String username,String password){
        try {
            RegisterHttp registerBes=new RegisterHttp();
            String res=registerBes.post(name,username,password);
            JSONObject jsonObject=new JSONObject(res);
            String islog=jsonObject.getString("status");
            if ("注册成功".equals(islog)){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
