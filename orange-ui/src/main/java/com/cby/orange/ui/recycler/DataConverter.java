package com.cby.orange.ui.recycler;

import java.util.ArrayList;

/**
 * 数据转换类
 * Created by baiyanfang on 2017/12/22.
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();

    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public String getmJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()){
            throw  new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }

    public DataConverter setJsonData(String mJsonData) {
        this.mJsonData = mJsonData;
        return this;
    }
}
