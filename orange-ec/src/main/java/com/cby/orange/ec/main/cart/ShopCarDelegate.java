package com.cby.orange.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cby.orange.delegate.bottom.BottomItemDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.utils.log.OrangeLogger;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by baiyanfang on 2017/12/21.
 */

public class ShopCarDelegate extends BottomItemDelegate implements ISuccess {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;

    private ShopCartAdapter mAdapter = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_shopping_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

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
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
