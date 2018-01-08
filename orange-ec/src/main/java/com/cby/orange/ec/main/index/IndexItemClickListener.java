package com.cby.orange.ec.main.index;

import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.detail.GoodsDetailDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by baiyanfang on 2017/12/26.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final OrangeDelegate DELEGATE;

    private IndexItemClickListener(OrangeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static IndexItemClickListener create(OrangeDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
        DELEGATE.getSupportDelegate().start(delegate);
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
