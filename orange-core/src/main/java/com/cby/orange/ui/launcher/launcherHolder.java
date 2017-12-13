package com.cby.orange.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by baiyanfang on 2017/12/13.
 */

public class launcherHolder implements Holder<Integer> {

    private AppCompatImageView mImageView = null ;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int i, Integer integer) {
        mImageView.setBackgroundResource(integer);
    }
}
