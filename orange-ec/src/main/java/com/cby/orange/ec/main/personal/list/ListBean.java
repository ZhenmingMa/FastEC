package com.cby.orange.ec.main.personal.list;

import android.widget.CompoundButton;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ui.recycler.ItemType;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class ListBean implements MultiItemEntity {

    private int mItemYype = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private OrangeDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mCheckedChangeListener = null;

    public ListBean(int mItemYype, String mImageUrl, String mText, String mValue, int mId, OrangeDelegate mDelegate, CompoundButton.OnCheckedChangeListener mCheckedChangeListener) {
        this.mItemYype = mItemYype;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mCheckedChangeListener = mCheckedChangeListener;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmText() {
        return mText;
    }

    public String getmValue() {
        return mValue;
    }

    public int getmId() {
        return mId;
    }

    public OrangeDelegate getmDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getmCheckedChangeListener() {
        return mCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemYype;
    }

    public static final class Builder {

        private int id = 0;
        private int itemYype = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private OrangeDelegate delegate = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemYype(int itemYype) {
            this.itemYype = itemYype;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setDelegate(OrangeDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public ListBean build(){
            return new ListBean(itemYype,imageUrl,text,value,id,delegate,onCheckedChangeListener);
        }
    }
}
