package com.cby.fastec;

import android.app.Application;
import android.widget.Toast;

import com.cby.orange.app.Orange;
import com.cby.orange.ec.icon.FontEcModule;
import com.joanzapata.iconify.Iconify;
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
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
