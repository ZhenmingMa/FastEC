package com.cby.fastec;

import android.app.Application;
import android.support.annotation.Nullable;

import com.cby.fastec.event.TestEvent;
import com.cby.orange.app.Orange;
import com.cby.orange.ec.datebase.DataBaseManager;
import com.cby.orange.ec.icon.FontEcModule;
import com.cby.orange.net.interceptors.DebugInterceptor;
import com.cby.orange.net.rx.AddCookieInterceptor;
import com.cby.orange.utils.callback.CallbackManager;
import com.cby.orange.utils.callback.CallbackType;
import com.cby.orange.utils.callback.IGlobalCallback;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Ma on 2017/11/22.
 */

public class FastECApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Orange.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://www.xiufm.com/RestServer/api/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("123121131213")
                .withWeChatAppSecret("12312213123")
                .withJavascriptInterface("orange")
                .withWebEvent("test", new TestEvent())
                //添加cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .withWebHost("https://www.baidu.com/")
                .configure();

        DataBaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(Orange.getApplicationContext());

        //控制推送的打开关闭
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Orange.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Orange.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Orange.getApplicationContext())) {
                            JPushInterface.stopPush(Orange.getApplicationContext());
                        }
                    }
                });

        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
