package com.cby.orange.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.ISuccess;

import butterknife.BindView;

/**
 * Created by baiyanfang on 2018/1/10.
 */

public class AddressDeleagate extends OrangeDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        AddressAdapter adapter = new AddressAdapter(new AddressDataConverter().setJsonData(response).convert());
        mRecyclerView.setAdapter(adapter);
    }
}
