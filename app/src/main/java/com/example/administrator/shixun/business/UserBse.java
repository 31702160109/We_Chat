package com.example.administrator.shixun.business;

import com.example.administrator.shixun.bean.UserBean;
import com.example.administrator.shixun.http.UserHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBse {
    private List<UserBean> list=null;
    public List<UserBean> getList(){
        try {
            UserHttp userHttp=new UserHttp();
            String res=userHttp.post();
            list=new ArrayList<UserBean>();
            JSONArray jsonArray=new JSONArray(res);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                UserBean userBean=new UserBean();
                userBean.setName(jsonObject.getString("name"));
                userBean.setUser(jsonObject.getString("user"));
                list.add(userBean);
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
