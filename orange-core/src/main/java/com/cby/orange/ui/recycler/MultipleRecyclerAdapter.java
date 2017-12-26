package com.cby.orange.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cby.orange.R;
import com.cby.orange.ui.banner.BannerCreator;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiyanfang on 2017/12/25.
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    //确保初始化一次bannar 防止重复加载
    private boolean mIsInitBanner = false;

    //设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    private MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }


    private void init() {
        //设置不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_iamge);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_iamge_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);

    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder multipleViewHolder, MultipleItemEntity multipleItemEntity) {

        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (multipleViewHolder.getItemViewType()) {
            case ItemType.TEXT:
                text = multipleItemEntity.getField(MultipleteFields.TEXT);
                multipleViewHolder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = multipleItemEntity.getField(MultipleteFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) multipleViewHolder.getView(R.id.image_single));

                break;
            case ItemType.TEXT_IMAGE:
                text = multipleItemEntity.getField(MultipleteFields.TEXT);
                imageUrl = multipleItemEntity.getField(MultipleteFields.IMAGE_URL);
                multipleViewHolder.setText(R.id.text_multiple, text);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) multipleViewHolder.getView(R.id.image_multiple));
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = multipleItemEntity.getField(MultipleteFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = multipleViewHolder.getView(R.id.bannar_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            default:
                break;
        }

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
        return getData().get(i).getField(MultipleteFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int i) {

    }
}
