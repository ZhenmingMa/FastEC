package com.cby.orange.ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.ec.main.personal.list.ListAdapter;
import com.cby.orange.ec.main.personal.list.ListBean;
import com.cby.orange.ec.main.personal.list.ListType;
import com.cby.orange.ec.main.personal.setting.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class UserProfileDelegate extends OrangeDelegate {


    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean.Builder()
                .setItemYype(ListType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();
        final ListBean name = new ListBean.Builder()
                .setItemYype(ListType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new NameDelegate())
                .setText("姓名")
                .setValue("未设置")
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemYype(ListType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置")
                .build();
        final ListBean birthday = new ListBean.Builder()
                .setItemYype(ListType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置")
                .build();
        List<ListBean> listBeans = new ArrayList<>();
        listBeans.add(image);
        listBeans.add(name);
        listBeans.add(gender);
        listBeans.add(birthday);

        //设置recycleview
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        ListAdapter adapter = new ListAdapter(listBeans);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
