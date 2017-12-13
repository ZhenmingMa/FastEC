package com.cby.orange.net.rx;

import android.content.Context;

import com.cby.orange.net.RestClient;
import com.cby.orange.net.RestCreator;
import com.cby.orange.net.callback.IError;
import com.cby.orange.net.callback.IFailure;
import com.cby.orange.net.callback.IRequest;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Ma on 2017/11/28.
 */

public class RxRestClientBuilder {
    private String mUrl = null;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mloaderStyle = null;

    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder row(String row){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),row);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle){
        this.mContext = context;
        this.mloaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mloaderStyle = LoaderStyle.BallGridPulseIndicator;
        return this;
}
    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS, mBody,mFile,
                                mloaderStyle,mContext);
    }

}
