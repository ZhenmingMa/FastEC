package com.cby.orange.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.net.callback.IFailure;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.ItemType;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleItemEntityBuilder;
import com.cby.orange.ui.recycler.MultipleteFields;

import java.util.ArrayList;

/**
 * Created by baiyanfang on 2017/12/22.
 */

public class IndexDataConvert extends DataConverter {


    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray  = JSON.parseObject(getmJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size ; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int goodsId = data.getInteger("goodsId");
            final int spanSize = data.getInteger("spanSize");
            final JSONArray banners = data.getJSONArray("banners");
            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null){
                type = ItemType.TEXT;
            }else if (imageUrl != null && text == null){
                type = ItemType.IMAGE;
            }else if (imageUrl != null){
                type = ItemType.TEXT_IMAGE;
            }else if (banners != null){
                type = ItemType.BANNER;
                //banner初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleteFields.ITEM_TYPE,type)
                    .setField(MultipleteFields.SPAN_SIZE,spanSize)
                    .setField(MultipleteFields.ID,goodsId)
                    .setField(MultipleteFields.TEXT,text)
                    .setField(MultipleteFields.IMAGE_URL,imageUrl)
                    .setField(MultipleteFields.BANNERS,bannerImages)
                    .build();
            ENTITYS.add(entity);
        }

        return ENTITYS;
    }
}
