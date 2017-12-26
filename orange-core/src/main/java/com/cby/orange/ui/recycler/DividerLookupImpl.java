package com.cby.orange.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by baiyanfang on 2017/12/25.
 */

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

    private final int COLOR;
    private final int SIZE;

    public DividerLookupImpl(int color, int size) {
        COLOR = color;
        SIZE = size;
    }


    @Override
    public Divider getVerticalDivider(int i) {
        return new Divider.Builder().color(COLOR).size(SIZE).build();
    }

    @Override
    public Divider getHorizontalDivider(int i) {
        return new Divider.Builder().color(COLOR).size(SIZE).build();
    }
}
