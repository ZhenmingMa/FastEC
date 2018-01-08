package com.cby.orange.ec.pay;

/**
 * Created by baiyanfang on 2018/1/4.
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
