package com.cby.orange.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.ItemType;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getmJsonData()).getJSONArray("data");
        ArrayList<MultipleItemEntity> list = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleteFields.ID, id)
                    .setField(MultipleteFields.TITLE, title)
                    .setField(MultipleteFields.IMAGE_URL, thumb)
                    .setField(OrderItemFIelds.PRICE, price)
                    .setField(OrderItemFIelds.TIME, time)
                    .build();
            list.add(entity);
        }
        return list;
    }
}
