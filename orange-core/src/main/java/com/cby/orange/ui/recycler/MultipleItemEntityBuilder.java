package com.cby.orange.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by baiyanfang on 2017/12/22.
 */

public class MultipleItemEntityBuilder {

    public static final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder(){
        //先清除之前的数据
        FIELDS.clear();
    }

    public static MultipleItemEntityBuilder builder(){
        return  new MultipleItemEntityBuilder();
    }

    public MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleteFields.ITEM_TYPE,itemType);
        return this;
    }

    public MultipleItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }

    public MultipleItemEntityBuilder setFields(WeakHashMap<?,?> fields){
        FIELDS.putAll(fields);
        return this;
    }

    public  MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }
}
