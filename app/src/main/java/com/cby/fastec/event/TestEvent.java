package com.cby.fastec.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.cby.orange.delegate.web.event.Event;
import com.cby.orange.utils.log.OrangeLogger;

/**
 * Created by baiyanfang on 2017/12/29.
 */

public class TestEvent extends Event{

    @Override
    public String execute(String params) {
        OrangeLogger.e("undefineEvent", getAction());
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall()",null);
                }
            });
        }
        return null;
    }
}
