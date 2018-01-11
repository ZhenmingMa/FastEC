package com.cby.orange.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleteFields;


import java.util.ArrayList;

/**
 * Created by baiyanfang on 2018/1/10.
 */

public class AddressDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getmJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleteFields.ID,id)
                    .setField(MultipleteFields.TAG,isDefault)
                    .setField(MultipleteFields.NAME,name)
                    .setField(AddressItemFields.ADDRESS,address)
                    .setField(AddressItemFields.PHONE,phone)

                    .build();
            ENTITYS.add(entity);
        }

        return ENTITYS;
    }
}
