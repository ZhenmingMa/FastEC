package com.cby.fastec.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.cby.fastec.MainActivity;
import com.cby.orange.utils.log.OrangeLogger;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by baiyanfang on 2018/1/10.
 */

public class MyDefinePushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        final Set<String> keys = bundle.keySet();
        JSONObject jsonObject = new JSONObject();
        for (String key:keys){
            final Object value = bundle.get(key);
            jsonObject.put(key,value);
        }
        OrangeLogger.e("pushReceiver",jsonObject.toJSONString());
        OrangeLogger.e("action",intent.getAction());

        final String pushAction = intent.getAction();
        if (pushAction.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){
            //处理接受到到信息
            onReceiverMessage(bundle);
        }else if (pushAction.equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            //打开对应到notification
            onOpenNotification(context,bundle);
        }

    }

    private void onReceiverMessage(Bundle bundle){
        final String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        final String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);
        final int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
        final String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
    }

    private void onOpenNotification(Context context, Bundle bundle) {
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final Bundle openActivityBundle = new Bundle();
        final Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(openActivityBundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ContextCompat.startActivity(context, intent, null);
    }

}
