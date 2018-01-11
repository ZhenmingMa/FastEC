package com.cby.orange.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cby.orange.ec.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListType.ITEM_SWITCH, R.layout.arrow_item_switch);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ListBean listBean) {
        switch (baseViewHolder.getItemViewType()) {
            case ListType.ITEM_NORMAL:
                baseViewHolder.setText(R.id.tv_arrow_text, listBean.getmText());
                baseViewHolder.setText(R.id.tv_arrow_value, listBean.getmValue());
                break;
            case ListType.ITEM_AVATAR:
                Glide.with(mContext)
                        .load(listBean.getmImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) baseViewHolder.getView(R.id.img_arrow_avatar));
                break;
            case ListType.ITEM_SWITCH:
                baseViewHolder.setText(R.id.tv_arrow_switch_text,listBean.getmText());
                SwitchCompat switchCompat = baseViewHolder.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(listBean.getmCheckedChangeListener());
                break;
            default:
                break;
        }
    }
}
