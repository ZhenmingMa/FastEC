package com.cby.orange.ec.main.personal.order;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cby.orange.ec.R;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleRecyclerAdapter;
import com.cby.orange.ui.recycler.MultipleViewHolder;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.cby.orange.utils.log.OrangeLogger;

import java.util.List;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {

    private final static RequestOptions OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().centerCrop();

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        OrangeLogger.e("item", JSON.toJSONString(data));
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.image_order_list);
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);

                final String titleVal = entity.getField(MultipleteFields.TITLE);
                final double priceVal = entity.getField(OrderItemFIelds.PRICE);
                final String timeVal = entity.getField(OrderItemFIelds.TIME);
                final String imageUrl = entity.getField(MultipleteFields.IMAGE_URL);


                title.setText(timeVal);
                price.setText("价格：" + String.valueOf((priceVal)));
                time.setText(timeVal);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(OPTIONS)
                        .into(imageView);

                break;
            default:
                break;
        }
    }
}
