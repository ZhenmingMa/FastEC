package com.cby.orange.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.app.AccountManager;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.datebase.DataBaseManager;
import com.cby.orange.ec.datebase.UserProfile;

/**
 * Created by baiyanfang on 2017/12/15.
 */

public class SignHandler {

    public static void onSignUp(String response,ISignListener mIsignListener){
        final JSONObject profilesJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profilesJson.getLong("userId");
        final String name = profilesJson.getString("name");
        final String avatar = profilesJson.getString("avatar");
        final String gender = profilesJson.getString("gender");
        final String address = profilesJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DataBaseManager.getInstance().getDao().insert(userProfile);

        //保存登陆状态
        AccountManager.setSignState(true);
        mIsignListener.onSignUpSuccess();

    }

    public static void onSignIn(String response,ISignListener mIsignListener){
        final JSONObject profilesJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profilesJson.getLong("userId");
        final String name = profilesJson.getString("name");
        final String avatar = profilesJson.getString("avatar");
        final String gender = profilesJson.getString("gender");
        final String address = profilesJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DataBaseManager.getInstance().getDao().insert(userProfile);

        //保存登陆状态
        AccountManager.setSignState(true);
        mIsignListener.onSignInSuccess();
    }

}
