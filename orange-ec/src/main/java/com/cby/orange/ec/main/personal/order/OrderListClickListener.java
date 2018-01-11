package com.cby.orange.ec.main.personal.order;

import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by baiyanfang on 2018/1/11.
 */

public class OrderListClickListener extends SimpleClickListener {

    private final OrangeDelegate DELEGATE;

    public OrderListClickListener(OrangeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        DELEGATE.getSupportDelegate().startWithPop(new OrderCommentDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
