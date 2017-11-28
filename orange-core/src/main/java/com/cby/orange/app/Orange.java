package com.cby.orange.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *
 * Created by Ma on 2017/11/22.
 */

public final class Orange {

    public static Configurator init(Context context){
        getConfigurators().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String,Object> getConfigurators(){
        return Configurator.getInstance().getOrangeConfigs();
    }
}
