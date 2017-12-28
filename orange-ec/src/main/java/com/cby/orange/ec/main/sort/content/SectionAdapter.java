package com.cby.orange.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cby.orange.ec.R;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baiyanfang on 2017/12/28.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    private static final RequestOptions REQUEST_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, SectionBean sectionBean) {
        baseViewHolder.setText(R.id.header, sectionBean.header);
        baseViewHolder.setVisible(R.id.more, sectionBean.isMore());
        baseViewHolder.addOnClickListener(R.id.more);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SectionBean sectionBean) {
        //item.t返回SectionBean类型
        final String thumb = sectionBean.t.getGoodsThumb();
        final String name = sectionBean.t.getGoodsName();
        final int goodsId = sectionBean.t.getGoodsId();
        final SectionContentItemEntity entity = sectionBean.t;
        baseViewHolder.setText(R.id.tv, name);
        final AppCompatImageView imageView = baseViewHolder.getView(R.id.iv);
        Glide.with(mContext)
                .load(thumb)
                .apply(REQUEST_OPTIONS)
                .into(imageView);
    }
}
