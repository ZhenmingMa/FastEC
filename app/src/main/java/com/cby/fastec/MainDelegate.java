package com.cby.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.net.callback.IError;
import com.cby.orange.net.callback.IFailure;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.net.RestClient;
import com.cby.orange.ui.LoaderStyle;
import com.cby.orange.ui.OrangeLoader;

/**
 * Created by Ma on 2017/11/28.
 */

public class MainDelegate extends OrangeDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        OrangeLoader.showLoading(getContext(), LoaderStyle.BallPulseIndicator);
//        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        Log.e("onsuccess",response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
