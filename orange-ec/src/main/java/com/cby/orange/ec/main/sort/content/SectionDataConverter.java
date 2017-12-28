package com.cby.orange.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.ui.recycler.DataConverter;
import com.cby.orange.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiyanfang on 2017/12/27.
 */

public class SectionDataConverter  {

    public static final ArrayList<SectionBean> convert(String json){
        final ArrayList<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //获取内容
            final SectionBean sectionTitleBean =  new SectionBean(true,title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setMore(true);

            //添加内容
            dataList.add(sectionTitleBean);

            final JSONArray goods = data.getJSONArray("goods");
            //商品内容
            final int goodsSize = goods.size();
            for (int j = 0; j < goodsSize ; j++) {
                final JSONObject good = goods.getJSONObject(j);
                final int goodId = good.getInteger("goods_id");
                final String goodsName = good.getString("goods_name");
                final String goodsThumb = good.getString("goods_thumb");
                //获取内容
                final SectionContentItemEntity entity = new SectionContentItemEntity();
                entity.setGoodsId(goodId);
                entity.setGoodsName(goodsName);
                entity.setGoodsThumb(goodsThumb);
                //添加内容
                dataList.add(new SectionBean(entity));
            }
            //商品内容循环结束
        }
        //Section循环结束
        return dataList;

    }
}
