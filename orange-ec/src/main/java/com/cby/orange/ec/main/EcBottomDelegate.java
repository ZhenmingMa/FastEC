package com.cby.orange.ec.main;

import android.graphics.Color;

import com.cby.orange.delegate.bottom.BaseBottomDelegate;
import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.orange.delegate.bottom.BottomTabBean;
import com.cby.orange.delegate.bottom.ItemBuilder;
import com.cby.orange.ec.main.discover.DiscoverDelegate;
import com.cby.orange.ec.main.index.IndexDelegate;
import com.cby.orange.ec.main.my.MyDelegate;
import com.cby.orange.ec.main.shoppingcar.ShoppingCarDelegate;
import com.cby.orange.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by baiyanfang on 2017/12/21.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShoppingCarDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new MyDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
