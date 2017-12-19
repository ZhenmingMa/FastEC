package com.cby.fastec;

import android.app.Application;

import com.cby.orange.app.Orange;
import com.cby.orange.ec.datebase.DataBaseManager;
import com.cby.orange.ec.icon.FontEcModule;
import com.cby.orange.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

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
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
        DataBaseManager.getInstance().init(this);
        initStetho();
    }

    private void initStetho(){
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    }
}
