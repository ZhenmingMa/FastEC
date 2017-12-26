package com.cby.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import com.cby.orange.activites.ProxyActivity;
import com.cby.orange.app.Orange;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.launcher.ILauncherListener;
import com.cby.orange.ec.launcher.LauncherDelegate;
import com.cby.orange.ec.launcher.LauncherScrollDelegate;
import com.cby.orange.ec.launcher.OnLauncherFinishTag;
import com.cby.orange.ec.main.EcBottomDelegate;
import com.cby.orange.ec.sign.ISignListener;
import com.cby.orange.ec.sign.SignInDelegate;
import com.cby.orange.ec.sign.SignUpDelegate;
import com.cby.orange.utils.log.OrangeLogger;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements ISignListener,ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Orange.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public OrangeDelegate setRootDelegate() {
        return new LauncherDelegate();
//        return new SignInDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登陆了", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登陆", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
