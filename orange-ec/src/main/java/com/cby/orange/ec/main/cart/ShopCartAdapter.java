package com.cby.orange.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cby.orange.app.Orange;
import com.cby.orange.ec.R;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.IError;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleRecyclerAdapter;
import com.cby.orange.ui.recycler.MultipleViewHolder;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.cby.orange.utils.log.OrangeLogger;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by baiyanfang on 2018/1/3.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectAll = false;

    private ICartItemListener mICartItemListener;
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        initPrice(data);

        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);

    }

    public void initPrice(List<MultipleItemEntity> data) {
        mTotalPrice = 0.0;
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice += total;
        }
    }

    public void setIsSelectAll(boolean isSelectAll) {
        this.mIsSelectAll = isSelectAll;
    }

    public void setICartItemListener(ICartItemListener iCartItemListener) {
        this.mICartItemListener = iCartItemListener;
    }

    public double getTotalPrice(){
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
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
                final IconTextView icon_minus = holder.getView(R.id.icon_item_minus);
                final AppCompatTextView tv_count = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView icon_plus = holder.getView(R.id.icon_item_plus);
                final IconTextView icon_isSelected = holder.getView(R.id.icon_item_shop_cart);
                //赋值
                tv_title.setText(title);
                tv_desc.setText(desc);
                tv_price.setText(String.valueOf(price));
                tv_count.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(iv_thumb);

                //左侧对勾渲染之前改变状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);

                //根据数据状态显示是否选中
                if (isSelected) {
                    icon_isSelected.setTextColor(ContextCompat.getColor(Orange.getApplicationContext(), R.color.app_main));
                } else {
                    icon_isSelected.setTextColor(Color.GRAY);
                }

                //icon点击事件
                icon_isSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            icon_isSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            icon_isSelected.setTextColor(ContextCompat.getColor(Orange.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });

                //加减按钮事件
                icon_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int count = entity.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(tv_count.getText().toString()) > 1) {
                            RestClient.builder().loader(mContext)
                                    .url("shop_cart_count.php")
                                    .params("count", count)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            OrangeLogger.d(response);
                                            int countNumber = Integer.parseInt(tv_count.getText().toString());
                                            countNumber--;
                                            tv_count.setText(String.valueOf(countNumber));
                                            if (mICartItemListener != null) {
                                                mTotalPrice -= price;
                                                final double itemTotal = countNumber * price;
                                                mICartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .error(new IError() {
                                        @Override
                                        public void onError(int code, String msg) {
                                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });
                icon_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int count = entity.getField(ShopCartItemFields.COUNT);
                        RestClient.builder().loader(mContext)
                                .url("shop_cart_count.php")
                                .params("count", count)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        OrangeLogger.d(response);
                                        int countNumber = Integer.parseInt(tv_count.getText().toString());
                                        countNumber++;
                                        tv_count.setText(String.valueOf(countNumber));
                                        if (mICartItemListener != null) {
                                            mTotalPrice += price;
                                            final double itemTotal = countNumber * price;
                                            mICartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .error(new IError() {
                                    @Override
                                    public void onError(int code, String msg) {
                                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                break;

            default:
                break;
        }
    }
}
