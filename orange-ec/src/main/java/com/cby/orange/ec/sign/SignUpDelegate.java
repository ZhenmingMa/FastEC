package com.cby.orange.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.IError;
import com.cby.orange.net.callback.IFailure;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.utils.log.OrangeLogger;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by baiyanfang on 2017/12/13.
 */

public class SignUpDelegate extends OrangeDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;

    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;

    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;

    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    @OnClick(R2.id.tv_link_sign_in)
    void OnclickSignIn() {
        getSupportDelegate().start(new SignInDelegate());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener)
            mISignListener = (ISignListener) activity;
    }

    @OnClick(R2.id.btn_sign_up_regisit)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://www.xiufm.com/RestServer/api/user_profile.php")
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            OrangeLogger.e("userProfile", response);
                            SignHandler.onSignUp(response, mISignListener);
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

    private boolean checkForm() {
        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String phone = mPhone.getText().toString();
        String password = mPassword.getText().toString();
        String rePassword = mRePassword.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请输入至少6位数的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
            mRePassword.setError("密码验证有误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
