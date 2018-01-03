package com.cby.orange.ec.main.cart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cby.orange.ec.R;
import com.cby.orange.ui.recycler.ItemType;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleRecyclerAdapter;
import com.cby.orange.ui.recycler.MultipleViewHolder;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 *
 * Created by baiyanfang on 2018/1/3.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:

                //先取出所有的值
                final int id = entity.getField(MultipleteFields.ID);
                final String thumb = entity.getField(MultipleteFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                //取出控件
                final AppCompatImageView iv_thumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tv_title = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tv_desc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tv_price = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView tv_minus = holder.getView(R.id.icon_item_minus);
                final AppCompatTextView tv_count = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView tv_plus = holder.getView(R.id.icon_item_plus);
                //赋值
                tv_title.setText(title);
                tv_desc.setText(desc);
                tv_price.setText(String.valueOf(price));
                tv_count.setText(String.valueOf(count));

                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(iv_thumb);
                break;

            default:
                break;
        }
    }
}
