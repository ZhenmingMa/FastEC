package com.cby.orange.ec.main.cart;

import android.service.quicksettings.Tile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleteFields;


import java.util.ArrayList;

/**
 * Created by baiyanfang on 2018/1/3.
 */

public class ShopCartDataConverter extends DataConverter {


    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getmJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("title");
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final double price = data.getDouble("price");
            final int count = data.getInteger("count");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleteFields.ITEM_TYPE, 6)
                    .setField(MultipleteFields.ID, id)
                    .setField(MultipleteFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.COUNT, count)
                    .build();
            dataList.add(entity);
        }

        return dataList;
    }
}
