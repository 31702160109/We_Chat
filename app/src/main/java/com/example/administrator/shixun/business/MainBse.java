package com.example.administrator.shixun.business;

import com.example.administrator.shixun.bean.MainBean;
import com.example.administrator.shixun.http.MainHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainBse {
    private List<MainBean> list=null;
    public List<MainBean> send(String key,String chat){
        try {
            MainHttp mainHttp=new MainHttp();
            String res=mainHttp.post(key,chat);
            list=new ArrayList<MainBean>();
            JSONArray jsonArray=new JSONArray(res);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                MainBean mainBean=new MainBean();
                mainBean.setChat(jsonObject.getString("chat"));
                mainBean.setTime(jsonObject.getString("time"));
                mainBean.setName(jsonObject.getString("name"));
                list.add(mainBean);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
