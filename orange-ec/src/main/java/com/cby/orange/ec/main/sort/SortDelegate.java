package com.cby.orange.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.main.sort.content.ContentDelegate;
import com.cby.orange.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by baiyanfang on 2017/12/21.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();

        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,listDelegate);

        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));

    }
}
