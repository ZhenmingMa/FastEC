package com.cby.orange.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.R2;
import com.cby.orange.ui.weight.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by baiyanfang on 2018/1/11.
 */

public class OrderCommentDelegate extends OrangeDelegate{

    @BindView(R2.id.custom_star_layout)
    StarLayout starLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickCommit(){
        Toast.makeText(getContext(), ""+starLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
