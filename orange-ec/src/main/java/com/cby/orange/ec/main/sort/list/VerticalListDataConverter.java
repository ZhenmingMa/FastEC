package com.cby.orange.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.app.Orange;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.ItemType;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleItemEntityBuilder;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.cby.orange.utils.log.OrangeLogger;


import java.util.ArrayList;

/**
 * Created by baiyanfang on 2017/12/26.
 */

public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON
                .parseObject(getmJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleteFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleteFields.ID, id)
                    .setField(MultipleteFields.TEXT, name)
                    .setField(MultipleteFields.TAG, false)
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleteFields.TAG, true);
        }
        return dataList;
    }
}
