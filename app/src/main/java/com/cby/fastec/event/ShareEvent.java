package com.cby.fastec.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cby.fastec.R;
import com.cby.orange.delegate.web.event.Event;
import com.cby.orange.utils.log.OrangeLogger;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by baiyanfang on 2018/1/22.
 */

public class ShareEvent extends Event {


    @Override
    public String execute(String params) {
         OrangeLogger.e("ShareEvent", params);
        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setTitleUrl("http://sharesdk.cn");
        oks.setText(text);
        oks.setUrl(url);
        oks.setImageUrl(imageUrl);
        oks.setComment("我是测试评论文本");
        oks.show(getContext());
        return null;
    }
}
