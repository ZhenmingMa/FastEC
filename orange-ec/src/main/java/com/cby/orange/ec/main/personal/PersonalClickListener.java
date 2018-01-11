package com.cby.orange.ec.main.personal;

import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.main.personal.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by baiyanfang on 2018/1/10.
 */

public class PersonalClickListener extends SimpleClickListener {

    private final OrangeDelegate DELEGATE;

    public PersonalClickListener(OrangeDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(i);
        int id = bean.getmId();
        switch (id) {
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            case 3:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            case 4:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            default:
                break;
        }
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
