package com.example.administrator.shixun.business;

import com.example.administrator.shixun.http.LoginHttp;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginBse {
    public Map<String,String> doLogin(String username,String password){
        try {
            LoginHttp loginHttp=new LoginHttp();
            String res=loginHttp.post(username,password);
            JSONObject jsonObject=new JSONObject(res);
            Map<String,String> map=new HashMap<String,String>();
            map.put("status",jsonObject.getString("status"));
            map.put("key",jsonObject.getString("user"));
            map.put("name",jsonObject.optString("name"));
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
