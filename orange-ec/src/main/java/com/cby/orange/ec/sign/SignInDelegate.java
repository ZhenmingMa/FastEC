package com.cby.orange.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.IError;
import com.cby.orange.net.callback.IFailure;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.utils.log.OrangeLogger;
import com.cby.orange.wechat.OrangeWeChat;
import com.cby.orange.wechat.callbacks.IWeChatSignInCallcback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by baiyanfang on 2017/12/14.
 */

public class SignInDelegate extends OrangeDelegate {

    @BindView(R2.id.edit_sign_in_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener)
            mISignListener = (ISignListener) activity;
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()) {
            RestClient.builder()
                    .url("user_profile.php")
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            OrangeLogger.e("userProfile", response);
                            SignHandler.onSignIn(response, mISignListener);
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

    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignUp(){
        getSupportDelegate().start(new SignUpDelegate(),SINGLETASK);
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        OrangeWeChat.getInstance().onSignInSuccess(new IWeChatSignInCallcback() {
            @Override
            public void onSignInSuccess(String userInfo) {
                OrangeLogger.e("userInfo",userInfo);
            }
        }).signIn();
    }

    private boolean checkForm(){
        String phone = mPhone.getText().toString();
        String password = mPassword.getText().toString();

        boolean isPass = true;

        if (phone.isEmpty()|| phone.length()!=11){
            mPhone.setError("手机号码错误");
            isPass = false;
        }else{
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length()<6){
            mPassword.setError("请输入至少6位数的密码");
            isPass = false;
        }else {
            mPassword.setError(null);
        }
        return isPass;

    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
