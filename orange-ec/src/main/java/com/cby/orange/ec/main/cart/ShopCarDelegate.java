package com.cby.orange.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.ec.pay.FastPay;
import com.cby.orange.ec.pay.IAlPayResultListener;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.utils.log.OrangeLogger;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by baiyanfang on 2017/12/21.
 */

public class ShopCarDelegate extends BottomItemDelegate implements ISuccess,ICartItemListener,IAlPayResultListener {

    private ShopCartAdapter mAdapter = null;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mViewStubCompat = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;
    private double mTotalPrice = 0.0;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectALl() {
        final int tag = (int) mIconSelectAll.getTag();
        if (mAdapter.getItemCount() > 0) {
            if (tag == 0) {
                mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
                mIconSelectAll.setTag(1);
                mAdapter.setIsSelectAll(true);
                //更新recycle的显示状态
                mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());

            } else {
                mIconSelectAll.setTextColor(Color.GRAY);
                mIconSelectAll.setTag(0);
                mAdapter.setIsSelectAll(false);
                //更新recycle的显示状态
                mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            }
            checkItemCount();
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickedClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        if (mAdapter.getItemCount() == 0) {
            return;
        }
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> newData = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (!isSelected) {
                newData.add(entity);
            }
        }
        mAdapter.setNewData(newData);
        mAdapter.initPrice(newData);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();

    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay(){
        createOrder();
//        FastPay.create(this).beginPayDialog();
    }

    //创建订单
    private void createOrder(){
        final String url = "index.php";
        final WeakHashMap<String,Object> orderParams = new WeakHashMap<>();
        orderParams.put("userId","123");
        orderParams.put("amount",0.01);
        orderParams.put("comment","测试支付");
        orderParams.put("type",1);
        orderParams.put("ordertype",0);
        orderParams.put("isanonymous",true);
        orderParams.put("followeduser",0);
        RestClient.builder()
                .url(url)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        final int orderId = JSON.parseObject(response).getInteger("result");
                        final int orderId = 1101;
                        //进行具体的支付
                        OrangeLogger.e("order",response);
                        FastPay.create(ShopCarDelegate.this)
                                .SetPayResultListener(ShopCarDelegate.this)
                                .setOrderID(orderId)
                                .beginPayDialog();



                    }
                })
                .build()
                .post();

    }

    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            @SuppressLint("RestrictedApi") final View stubView = mViewStubCompat.inflate();
            final AppCompatTextView tv_to_buy =
                    (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tv_to_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你该购物啦", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setClickable(false);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mIconSelectAll.setClickable(true);
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shopping_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        OrangeLogger.d(response);
        final ArrayList<MultipleItemEntity> data = new ShopCartDataConverter().setJsonData(response).convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setICartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
