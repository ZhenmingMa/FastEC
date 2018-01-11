package com.cby.orange.ec.main.personal.setting;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.ec.main.personal.list.ListAdapter;
import com.cby.orange.ec.main.personal.list.ListBean;
import com.cby.orange.ec.main.personal.list.ListType;
import com.cby.orange.utils.callback.CallbackManager;
import com.cby.orange.utils.callback.CallbackType;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import butterknife.BindView;

/**
 * Created by baiyanfang on 2018/1/11.
 */

public class SettingDelegate extends OrangeDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean push = new ListBean.Builder()
                .setItemYype(ListType.ITEM_SWITCH)
                .setId(1)
                .setText("消息推送")
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            Toast.makeText(_mActivity, "打开推送", Toast.LENGTH_SHORT).show();
                        } else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            Toast.makeText(_mActivity, "关闭推送", Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemYype(ListType.ITEM_NORMAL)
                .setId(2)
                .setText("关于我们")
                .setDelegate(new AboutDelegate())
                .build();

        List<ListBean> list = new ArrayList<>();
        list.add(push);
        list.add(about);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        ListAdapter adapter = new ListAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SeetingClickListener(this));
    }

}
