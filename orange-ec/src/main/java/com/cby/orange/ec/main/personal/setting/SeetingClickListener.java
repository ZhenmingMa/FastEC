package com.cby.orange.ec.main.personal.setting;

import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.main.personal.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by baiyanfang on 2018/1/11.
 */

public class SeetingClickListener extends SimpleClickListener {
    private final OrangeDelegate DELDEGATE;

    public SeetingClickListener(OrangeDelegate deldegate) {
        DELDEGATE = deldegate;
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(i);
        final int id = bean.getmId();
        switch (id) {
            case 1:
                //推送开关
                break;
            case 2:
                //关于我们
                DELDEGATE.getSupportDelegate().start(bean.getmDelegate());
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
