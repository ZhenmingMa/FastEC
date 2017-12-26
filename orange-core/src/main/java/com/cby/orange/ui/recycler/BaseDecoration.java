package com.cby.orange.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by baiyanfang on 2017/12/25.
 */

public class BaseDecoration extends DividerItemDecoration {

    /**
     * 工厂方法
     */
    private BaseDecoration(@ColorInt int color,int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color,size);
    }
}
