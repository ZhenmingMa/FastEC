package com.cby.orange.ec.main.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.orange.ec.R;

/**
 * Created by baiyanfang on 2017/12/21.
 */

public class FindDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_compass;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
